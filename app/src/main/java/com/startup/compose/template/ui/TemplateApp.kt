package com.startup.compose.template.ui

import android.content.res.Resources
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.consumedWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.startup.compose.template.R
import com.startup.compose.template.manager.INetworkManager
import com.startup.compose.template.ui.route.EventManager
import com.startup.compose.template.ui.route.NavGraph


@OptIn(
    ExperimentalLifecycleComposeApi::class,
    ExperimentalLayoutApi::class,
    ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class
)
@Composable
fun TemplateApp(
    eventManager: EventManager,
    networkMonitor: INetworkManager
) {
    val appState =
        rememberAppState(networkMonitor = networkMonitor, eventManager = eventManager)


    Scaffold(
        snackbarHost = { SnackbarHost(appState.snackbarHostState) },
    ) { padding ->
        val isOffline by appState.isOffline.collectAsStateWithLifecycle()
        val notConnected = stringResource(R.string.not_connected)

        LaunchedEffect(isOffline) {
            if (isOffline) appState.snackbarHostState.showSnackbar(
                message = notConnected,
                duration = SnackbarDuration.Indefinite
            )
        }
        NavGraph(
            modifier = Modifier
                .padding(padding)
                .consumedWindowInsets(padding),
            navController = appState.navController,
        )
    }
}

@Composable
@ReadOnlyComposable
fun resources(): Resources {
    LocalConfiguration.current
    return LocalContext.current.resources
}