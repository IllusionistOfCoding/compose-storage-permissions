package com.startup.compose.template.ui.screen.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.startup.compose.template.di.MainDispatcher
import com.startup.compose.template.ui.route.EventManager
import com.startup.compose.template.ui.route.NavigationEvent
import com.startup.compose.template.ui.route.SnackbarEvent
import com.startup.compose.template.util.loge
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val stateHandle: SavedStateHandle,
    @MainDispatcher private val dispatcher: CoroutineDispatcher,
    private val eventManager: EventManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    init {
    }


    fun onNavigateTo(event: NavigationEvent) {
        viewModelScope.launch {
            try {
                eventManager.sendEvent(event)
            } catch (e: Exception) {
                val errorMsg = e.message ?: "Something wrong happened. Please try again."
                sendErrorMessage(SnackbarEvent.StringSnackbar(errorMsg))
                loge(errorMsg, e)
            }
        }
    }

    private fun sendErrorMessage(event: SnackbarEvent) {
        viewModelScope.launch {
            try {
                eventManager.sendEvent(event)
            } catch (e: Exception) {
                val errorMsg = e.message ?: "Something wrong happened. Please try again."
                loge(errorMsg, e)
            }
        }
    }
}

data class HomeUiState(
    val loading: Boolean = false
)