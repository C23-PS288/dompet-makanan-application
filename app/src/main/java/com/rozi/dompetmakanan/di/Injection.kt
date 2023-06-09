package com.rozi.dompetmakanan.di

import android.content.Context
import com.rozi.dompetmakanan.data.remote.retrofit.ApiConfig
import com.rozi.dompetmakanan.data.repository.FoodRepository
import com.rozi.dompetmakanan.data.repository.PredictRepository
import com.rozi.dompetmakanan.data.repository.UserRepository

object Injection {

    fun provideUserRepository(context: Context) : UserRepository {
        val apiService = ApiConfig.getApiService()
        return UserRepository(apiService, context)
    }

    fun provideFoodRepository(context: Context) : FoodRepository {
        val apiService = ApiConfig.getApiService()
        return FoodRepository(apiService, context)
    }
    fun providePredictRepository(context: Context) : PredictRepository {
        val apiServiceML = ApiConfig.getApiServiceTensorFlow()
        val apiService = ApiConfig.getApiService()
        return PredictRepository(apiService,apiServiceML, context)
    }

}