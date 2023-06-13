package com.rozi.dompetmakanan.data.repository

import android.content.Context
import com.rozi.dompetmakanan.data.remote.response.PredictResponse
import com.rozi.dompetmakanan.data.remote.retrofit.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class PredictRepository(private  val apiService: ApiService, context: Context) {

    suspend fun uploadImage(file: File) : Flow<String> {
        val requestImageFile = file.asRequestBody("image/jpeg".toMediaType())
        val imageMultipart : MultipartBody.Part = MultipartBody.Part.createFormData(
            "file",
            file.name,
            requestImageFile
        )
        val upload = apiService.predictImage(imageMultipart)
        val uri = upload.predictedClassLabel
        return flowOf(uri)
    }
}