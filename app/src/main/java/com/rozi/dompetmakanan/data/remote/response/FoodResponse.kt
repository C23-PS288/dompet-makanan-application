package com.rozi.dompetmakanan.data.remote.response

import com.rozi.dompetmakanan.model.Food

data class FoodResponse(
	val code: String,
	val data: List<Food>,
	val status: String
)


