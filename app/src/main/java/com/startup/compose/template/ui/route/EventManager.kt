package com.startup.compose.template.ui.route

import androidx.annotation.StringRes
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

sealed class Event
sealed class NavigationEvent: Event() {
    object Back : NavigationEvent()
    object Home : NavigationEvent()
}

sealed class SnackbarEvent: Event() {
    data class StringSnackbar(val message: String) : SnackbarEvent()
    data class ResourceSnackbar(@StringRes val message: Int) : SnackbarEvent()
}

object EventManager {

    private val _eventShared = MutableSharedFlow<Event>(replay = 0)
    val eventShared: SharedFlow<Event> = _eventShared

    suspend fun sendEvent(event: Event) {
        _eventShared.emit(manageDestination(event))
    }

    private fun manageDestination(event: Event): Event =
        event

}