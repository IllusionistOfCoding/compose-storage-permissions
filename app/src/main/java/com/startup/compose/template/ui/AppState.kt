package com.startup.compose.template.ui

import android.content.res.Resources
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.startup.compose.template.manager.INetworkManager
import com.startup.compose.template.ui.route.AppDestinations
import com.startup.compose.template.ui.route.EventManager
import com.startup.compose.template.ui.route.NavigationEvent
import com.startup.compose.template.ui.route.NavigationManager
import com.startup.compose.template.util.loge
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@Composable
fun rememberAppState(
    networkMonitor: INetworkManager,
    eventManager: EventManager,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController(),
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
    resources: Resources = resources(),
): AppState {
    return remember(
        networkMonitor,
        eventManager,
        coroutineScope,
        navController,
        snackbarHostState,
        resources
    ) {
        AppState(
            navController,
            coroutineScope,
            snackbarHostState,
            resources,
            eventManager,
            networkMonitor
        )
    }
}

@Stable
class AppState(
    val navController: NavHostController,
    coroutineScope: CoroutineScope,
    val snackbarHostState: SnackbarHostState,
    private val resources: Resources,
    eventManager: EventManager,
    networkMonitor: INetworkManager,
) {
    //TODO optimization evaluateEvent
    init {
        evaluateEvent(coroutineScope, eventManager, NavigationManager(navController), snackbarHostState, resources)
    }

    //TODO move to navigationManager
    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val isOffline = networkMonitor.isOnline
        .map(Boolean::not)
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = false
        )
}

fun evaluateEvent(
    coroutineScope: CoroutineScope,
    eventManager: EventManager,
    navigationManager: NavigationManager,
    snackbarHostState: SnackbarHostState,
    resources: Resources,
) {
    coroutineScope.launch {
        try {
            eventManager.eventShared.collectLatest {
                when (it) {
                    is NavigationEvent.Back -> navigationManager.onBack()
                    is NavigationEvent.Home -> navigationManager.navigateTo(AppDestinations.HOME_ROUTE)
                    else -> {}
                }
            }
        } catch (e: Exception) {
            loge(e.message ?: "", e)
        }
    }
}