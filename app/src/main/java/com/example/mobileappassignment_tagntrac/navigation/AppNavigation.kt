package com.example.mobileappassignment_tagntrac.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mobileappassignment_tagntrac.viewmodel.SignInViewModel
import com.example.mobileappassignment_tagntrac.views.auth.WorkspaceSignInBaseView
import com.example.mobileappassignment_tagntrac.views.auth.WorkspaceSignInView
import com.example.mobileappassignment_tagntrac.views.home.HomeBaseView
import kotlinx.coroutines.launch

@Composable
fun AppNavigation(modifier: Modifier, homeViewModel: SignInViewModel) {
    val navController = rememberNavController()
    val context = LocalContext.current
    val dataStoreManager = remember { DataStoreManager(context) }
    val isLoggedInFlow = dataStoreManager.isLoggedIn.collectAsState(initial = false)
    val isLoggedIn = isLoggedInFlow.value
    val startDestination = if (isLoggedIn) Screens.homeScreen else Screens.signInBaseScreen

    NavHost(navController = navController, startDestination = startDestination) {
        composable(Screens.signInBaseScreen) {
            WorkspaceSignInBaseView(homeViewModel) {
                navController.navigate(Screens.signInScreen)
            }
        }
        composable(Screens.signInScreen) {
            WorkspaceSignInView(homeViewModel) {
                // Save the login state to DataStore
                (context as? androidx.lifecycle.LifecycleOwner)?.lifecycleScope?.launch {
                    dataStoreManager.setLoggedIn(true)
                }
                navController.navigate(Screens.homeScreen) {
                    popUpTo(Screens.signInBaseScreen) {
                        inclusive = true
                    }
                }
            }
        }
        composable(Screens.homeScreen) {
            HomeBaseView(homeViewModel) {
                // Clearing login state when logging out
                (context as? androidx.lifecycle.LifecycleOwner)?.lifecycleScope?.launch {
                    dataStoreManager.setLoggedIn(false)
                }
                navController.navigate(Screens.signInBaseScreen) {
                    popUpTo(Screens.homeScreen) {
                        inclusive = true
                    }
                }
            }
        }
    }
}