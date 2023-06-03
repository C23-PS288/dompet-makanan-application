package com.rozi.dompetmakanan.data.remote.response

data class RegisterResponse(
	val code: String,
	val data: Data,
	val status: String
)

data class Data(
	val createdAt: String,
	val password: String,
	val name: String,
	val id: Int,
	val email: String,
	val updatedAt: String
)


