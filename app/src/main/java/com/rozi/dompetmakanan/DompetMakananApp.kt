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
import com.rozi.dompetmakanan.ui.screen.login.LoginScreen
import com.rozi.dompetmakanan.ui.screen.login.LoginViewModel
import com.rozi.dompetmakanan.ui.screen.register.RegisterScreen
import com.rozi.dompetmakanan.ui.screen.register.RegisterViewModel
import com.rozi.dompetmakanan.utils.ViewModelFactory

@Composable
fun DompetMakananApp(application: Application) {
    val loginViewModel: LoginViewModel = viewModel(
        factory = ViewModelFactory.getInstance(application)
    )
    val registerViewModel: RegisterViewModel = viewModel(
        factory = ViewModelFactory.getInstance(application)
    )
    val navController = rememberNavController()
    val loginLoadingProgressBar = loginViewModel.progressBar.value
    val registerLoadingProgressBar = registerViewModel.progressBar.value


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
                    navController.popBackStack()
                    navController.navigate(route = Destination.Home.route)
                }
            } else {
                LoginScreen(
                    loadingProgressBar = loginLoadingProgressBar,
                    onclickLogin = loginViewModel::login,
                    onClickRegister = { navController.navigate(Destination.Register.route) },
                    onDismiss = {loginViewModel.snackbarError.value = false},
                )
            }
        }

        composable(route = Destination.Register.route) {
            if (registerViewModel.isSuccessLoading.value) {
                LaunchedEffect(key1 = Unit) {
                    navController.navigate(route = Destination.Login.route) {
                        launchSingleTop = true
                    }
                }
            } else {
                RegisterScreen(
                    onClickLogin = {
                        navController.navigate(Destination.Login.route) {
                            popUpTo(Destination.Login.route) {
                                inclusive = true
                            }
                        }
                    },
                    onClickRegister = registerViewModel::register,
                    loadingProgressBar = registerLoadingProgressBar
                )
            }
        }

        composable(route = Destination.Home.route) {
            HomeScreen(application = application, navController = navController)
        }
    }
}