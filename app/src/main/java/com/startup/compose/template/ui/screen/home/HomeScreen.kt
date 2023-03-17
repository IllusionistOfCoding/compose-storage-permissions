package com.startup.compose.template.ui.screen.home

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.startup.compose.template.ui.route.NavigationEvent
import com.startup.compose.template.ui.theme.StartupComposeTemplateTheme

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    HomeContent(
        uiState = uiState,
        onNavigateTo = viewModel::onNavigateTo,
    )
}

@Composable
fun HomeContent(
    uiState: HomeUiState,
    onNavigateTo: (NavigationEvent) -> Unit
) {
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier
            .navigationBarsPadding()
            .fillMaxSize()
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(
                    bottom = paddingValues.calculateBottomPadding()
                )
                .fillMaxSize()
                .background(Color.White)
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Welcome!",
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    StartupComposeTemplateTheme {
        HomeContent(
            uiState = HomeUiState(),
            onNavigateTo = {},
        )
    }
}