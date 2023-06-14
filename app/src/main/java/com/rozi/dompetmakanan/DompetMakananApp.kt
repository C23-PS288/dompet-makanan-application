package com.rozi.dompetmakanan

import android.app.Application
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.rozi.dompetmakanan.ui.components.BottomBar
import com.rozi.dompetmakanan.ui.navigation.Destination
import com.rozi.dompetmakanan.ui.screen.SplashScreen
import com.rozi.dompetmakanan.ui.screen.camera.ScanImageScreen
import com.rozi.dompetmakanan.ui.screen.home.HomeScreen
import com.rozi.dompetmakanan.ui.screen.login.LoginScreen
import com.rozi.dompetmakanan.ui.screen.profile.ProfileScreen
import com.rozi.dompetmakanan.ui.screen.register.RegisterScreen
import com.rozi.dompetmakanan.ui.screen.register.RegisterViewModel
import com.rozi.dompetmakanan.ui.screen.resultPredict.ResultPredictScreen
import com.rozi.dompetmakanan.utils.ViewModelFactory

@ExperimentalMaterialApi
@Composable
fun DompetMakananApp(application: Application) {
    val registerViewModel: RegisterViewModel = viewModel(
        factory = ViewModelFactory.getInstance(application)
    )
    val navController = rememberNavController()
    val registerLoadingProgressBar = registerViewModel.progressBar.value

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute != Destination.Login.route && currentRoute != Destination.Register.route && currentRoute != Destination.SplashScreen.route) {
                BottomBar(navController)
            }
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = Destination.getStartDestination()
        ) {

            composable(route = Destination.SplashScreen.route) {
                SplashScreen(
                    navToHome = {
                        navController.popBackStack()
                        navController.navigate(Destination.Home.route)
                    },
                    navToLogin = {
                        navController.popBackStack()
                        navController.navigate(Destination.Login.route)
                    }
                )
            }

            composable(route = Destination.Login.route) {
                LoginScreen(
                    onClickRegister = { navController.navigate(Destination.Register.route) },
                    application = application,
                    onLoginState = {
                        navController.popBackStack()
                        navController.navigate(route = Destination.Home.route) {
                            launchSingleTop = true
                        }
                    }
                )
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

            composable(route = Destination.Camera.route) {
                ScanImageScreen(
                    context = application,
                    onSuccess = {
                        navController.navigate(Destination.ResultPredict.createRoute(it))
                    }
                )
            }

            composable(
                route = Destination.ResultPredict.route,
                arguments = listOf(navArgument("uri") {type = NavType.StringType})
            ) {
                val uri = it.arguments?.getString("uri") ?:""
                ResultPredictScreen(application = application, uri = uri)
            }

            composable(route = Destination.Profile.route) {
                ProfileScreen(navController = navController)
            }
        }
    }
}