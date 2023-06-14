package com.rozi.dompetmakanan.data.repository

import android.content.Context
import com.rozi.dompetmakanan.data.lokal.UserPreferences
import com.rozi.dompetmakanan.data.remote.retrofit.ApiService
import com.rozi.dompetmakanan.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class UserRepository(private val apiService : ApiService, context: Context) {

    private val preferences = UserPreferences(context)

    suspend fun getUserWithId(): Flow<User> {
        val client = apiService.getUserWithId(preferences.getId())
        return flowOf(client.data)
    }
}