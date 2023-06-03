package com.rozi.dompetmakanan.model

data class User(
    val name: String,
    val id: Int,
    val accessToken: String,
    val email: String,
    val refreshToken: String
)
