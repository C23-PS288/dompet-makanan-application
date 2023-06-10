package com.rozi.dompetmakanan.di

import android.content.Context
import com.rozi.dompetmakanan.data.remote.retrofit.ApiConfig
import com.rozi.dompetmakanan.data.repository.UserRepository

object Injection {

    fun provideUserRepository(context: Context) : UserRepository {
        val apiService = ApiConfig.getApiService()
        return UserRepository(apiService, context)
    }
}