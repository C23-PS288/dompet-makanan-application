package com.rozi.dompetmakanan.data.remote.response

import com.rozi.dompetmakanan.model.User

data class LoginResponse(
	val code: String,
	val data: User,
	val status: String
)



