package com.example

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import com.example.ui.components.GojoBottomBar
import com.example.ui.components.NavigationTab
import com.example.ui.screens.auth.AuthScreen
import com.example.ui.screens.chat.ChatScreen
import com.example.ui.screens.dashboard.DashboardScreen
import com.example.ui.screens.profile.ProfileScreen
import com.example.ui.screens.projects.ProjectsScreen
import com.example.ui.theme.CosmicBackground
import com.example.ui.theme.GojoAITheme
import com.example.viewmodel.GojoViewModel

class MainActivity : ComponentActivity() {
    private val viewModel: GojoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                GojoAITheme {
                    val isLoggedIn by viewModel.isLoggedIn.collectAsState()
                    val currentTab by viewModel.currentTab.collectAsState()
                    val toastMsg by viewModel.toastMessage.collectAsState()
                    val context = LocalContext.current

                    LaunchedEffect(toastMsg) {
                        toastMsg?.let {
                            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                            viewModel.clearToast()
                        }
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(CosmicBackground)
                    ) {
                        Crossfade(targetState = isLoggedIn, label = "auth_crossfade") { loggedIn ->
                            if (!loggedIn) {
                                AuthScreen(viewModel = viewModel)
                            } else {
                                MainAppContent(
                                    currentTab = currentTab,
                                    viewModel = viewModel
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MainAppContent(
    currentTab: NavigationTab,
    viewModel: GojoViewModel
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = CosmicBackground,
        bottomBar = {
            GojoBottomBar(
                currentTab = currentTab,
                onTabSelected = { viewModel.setTab(it) }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = innerPadding.calculateBottomPadding())
        ) {
            Crossfade(targetState = currentTab, label = "tab_crossfade") { tab ->
                when (tab) {
                    NavigationTab.DASHBOARD -> DashboardScreen(viewModel = viewModel)
                    NavigationTab.CHAT -> ChatScreen(viewModel = viewModel)
                    NavigationTab.PROJECTS -> ProjectsScreen(viewModel = viewModel)
                    NavigationTab.PROFILE -> ProfileScreen(viewModel = viewModel)
                }
            }
        }
    }
}
