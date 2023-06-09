package com.rozi.dompetmakanan.ui.screen.home

import com.rozi.dompetmakanan.R

data class Menu(
    val image: Int,
    val Title: String,
    val descripton: String,
    val price: String,
)

val dummyMenu = listOf(
    Menu(R.drawable.banner, "Menu Makanan 1", "Deskripsi", "Rp. 90.000"),
    Menu(R.drawable.banner, "Menu Makanan 2", "Deskripsi", "Rp. 90.000"),
    Menu(R.drawable.banner, "Menu Makanan 3", "Deskripsi", "Rp. 90.000"),
    Menu(R.drawable.banner, "Menu Makanan 4", "Deskripsi", "Rp. 90.000"),
    Menu(R.drawable.banner, "Menu Makanan 5", "Deskripsi", "Rp. 90.000"),
    Menu(R.drawable.banner, "Menu Makanan 6", "Deskripsi", "Rp. 90.000"),
)