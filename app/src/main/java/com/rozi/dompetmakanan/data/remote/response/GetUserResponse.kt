package com.rozi.dompetmakanan.data.remote.response

import com.rozi.dompetmakanan.model.User

data class GetUserResponse(
    val code: String,
    val data: User,
    val status: String
)