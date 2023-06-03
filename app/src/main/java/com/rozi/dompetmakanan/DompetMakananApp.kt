package com.rozi.dompetmakanan

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rozi.dompetmakanan.ui.navigation.Destination
import com.rozi.dompetmakanan.ui.screen.SplashScreen
import com.rozi.dompetmakanan.ui.screen.home.HomeScreen
import com.rozi.dompetmakanan.ui.screen.login.LoginContent
import com.rozi.dompetmakanan.ui.screen.login.LoginViewModel
import com.rozi.dompetmakanan.utils.ViewModelFactory

@Composable
fun DompetMakananApp(application: Application) {
    val loginViewModel: LoginViewModel = viewModel(
        factory = ViewModelFactory.getInstance(application)
    )
    val navController = rememberNavController()
    val loadingProgressBar = loginViewModel.progressBar.value

    NavHost(
        navController = navController,
        startDestination = Destination.getStartDestination()
    ) {

        composable(route = Destination.SplashScreen.route) {
            SplashScreen(navController = navController)
        }

        composable(route = Destination.Login.route) {
            if (loginViewModel.isSuccessLoading.value) {
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
                    onclickLogin = loginViewModel::login
                )
            }
        }

        composable(route = Destination.Home.route) {
            HomeScreen()
        }
    }
}