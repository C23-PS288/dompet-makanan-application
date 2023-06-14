package com.rozi.dompetmakanan.data.repository

import android.content.Context
import com.rozi.dompetmakanan.data.lokal.TokenPreferences
import com.rozi.dompetmakanan.data.lokal.UserPreferences
import com.rozi.dompetmakanan.data.remote.retrofit.ApiService
import com.rozi.dompetmakanan.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class UserRepository(private val apiService : ApiService, context: Context) {

    private val userPreferences = UserPreferences(context)
    private val tokenPreferences = TokenPreferences(context)
    val token = "Bearer ${tokenPreferences.getToken()}"

    suspend fun getUserWithId(): Flow<User> {
        val client = apiService.getUserWithId(token, userPreferences.getId())
        return flowOf(client.data)
    }
}