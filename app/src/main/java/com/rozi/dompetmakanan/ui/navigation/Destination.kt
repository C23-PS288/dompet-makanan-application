package com.rozi.dompetmakanan.ui.navigation

sealed class Destination(val route : String) {
    object Login : Destination(route = "login")
    object Register : Destination(route = "register")
    object Home : Destination(route = "home")
    object SplashScreen : Destination(route = "splashscreen")
    object Camera : Destination(route = "camera")
    object Profile : Destination(route = "profile")

    companion object {
        fun getStartDestination() = SplashScreen.route
    }
}