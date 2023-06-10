package com.rozi.dompetmakanan.model

data class User(
    val name: String? = null,
    val id: Int? = null,
    val accessToken: String? = null,
    val email: String? = null,
    val phone: String? = null,
    val refreshToken: String? = null
)
