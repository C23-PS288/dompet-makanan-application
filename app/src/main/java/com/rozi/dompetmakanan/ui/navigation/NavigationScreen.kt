package com.rozi.dompetmakanan.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rozi.dompetmakanan.ui.screen.home.HomeScreen
import com.rozi.dompetmakanan.ui.screen.login.LoginContent
import com.rozi.dompetmakanan.ui.screen.login.LoginViewModel

@Composable
fun NavigationScreen(viewModel: LoginViewModel) {
    val navController = rememberNavController()
    val loadingProgressBar = viewModel.progressBar.value

    NavHost(
        navController = navController,
        startDestination = Destination.getStartDestination()
    ) {
        composable(route = Destination.Login.route) {
            if (viewModel.isSuccessLoading.value) {
                LaunchedEffect(key1 = Unit) {
                    navController.navigate(route = Destination.Home.route) {
                        popUpTo(route = Destination.Login.route) {
                            inclusive = true
                        }
                    }
                }
            } else {
                LoginContent(
                    loadingProgressBar = loadingProgressBar,
                    onclickLogin = viewModel::login
                )
            }
        }

        composable(route = Destination.Home.route){
            HomeScreen()
        }
    }
}