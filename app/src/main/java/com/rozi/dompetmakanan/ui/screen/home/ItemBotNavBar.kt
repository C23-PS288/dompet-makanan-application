package com.rozi.dompetmakanan.ui.screen.home

import com.rozi.dompetmakanan.R

sealed class ItemBotNavBar(
    val route: String,
    val title: String,
    val image: Int,
){
    object Home:ItemBotNavBar("home", "Home", R.drawable.home)
    object Favorite:ItemBotNavBar("favorite", "Favorite", R.drawable.favorite)
    object Camera:ItemBotNavBar("camera", "Camera", R.drawable.camera)
}