package com.rozi.dompetmakanan.ui.navigation

sealed class Destination(val route : String) {
    object Login : Destination(route = "login")
    object Home : Destination(route = "home")
    object SplashScreen : Destination(route = "splashscreen")

    companion object {
        fun getStartDestination() = SplashScreen.route
    }
}