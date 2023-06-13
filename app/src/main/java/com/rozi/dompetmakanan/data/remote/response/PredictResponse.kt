package com.rozi.dompetmakanan.data.remote.response

import com.google.gson.annotations.SerializedName

data class PredictResponse(
	@field:SerializedName("predicted_class_label")
	val predictedClassLabel: String
)

