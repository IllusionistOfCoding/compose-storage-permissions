package com.startup.compose.template.ui.route

import androidx.navigation.NavHostController

/**
 * Destinations used in the [SportLifeAppState].
 */
object AppDestinations {
    const val HOME_ROUTE = "home"
}


/**
 * Models the navigation actions in the app.
 * [popUpTo] Pop up to the start destination of the graph to avoid building up a large stack of
 * destinations on the back stack as users select items.
 * [inclusive] remove previous Composable from back stack.
 * [launchSingleTop] Avoid multiple copies of the same destination when re-selecting the same item.
 * [restoreState] estore state when re-selecting a previously selected item
 */
class NavigationManager(navController: NavHostController) {
    val navigateTo: (route: String) -> Unit = {
        navController.navigate(it) {
            launchSingleTop = true
            restoreState = true
        }
    }

    val onBack: () -> Unit = {
        navController.popBackStack()
    }

}