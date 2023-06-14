package com.rozi.dompetmakanan.data.repository

import android.content.Context
import com.rozi.dompetmakanan.data.lokal.TokenPreferences
import com.rozi.dompetmakanan.data.remote.response.PredictResponse
import com.rozi.dompetmakanan.data.remote.retrofit.ApiService
import com.rozi.dompetmakanan.model.Food
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class PredictRepository(private  val apiService: ApiService, private val apiServiceML : ApiService, context: Context) {

    private val tokenPreferences = TokenPreferences(context)
    val token = "Bearer ${tokenPreferences.getToken()}"
    suspend fun predictImage(file: File) : Flow<List<Food>> {
        val requestImageFile = file.asRequestBody("image/jpeg".toMediaType())
        val imageMultipart : MultipartBody.Part = MultipartBody.Part.createFormData(
            "file",
            file.name,
            requestImageFile
        )
        val upload = apiServiceML.predictImage(imageMultipart)
        val uri = upload.predictedClassLabel

        val predict = apiService.getFoodByKategori(token,uri)
        return flowOf(predict.data)
    }
}