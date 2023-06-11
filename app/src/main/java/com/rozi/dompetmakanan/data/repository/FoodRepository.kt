package com.rozi.dompetmakanan.data.repository

import android.content.Context
import com.rozi.dompetmakanan.data.lokal.TokenPreferences
import com.rozi.dompetmakanan.data.remote.retrofit.ApiService
import com.rozi.dompetmakanan.model.Food
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FoodRepository(private val apiService: ApiService, context: Context) {
    private val preferences = TokenPreferences

    suspend fun getAllFoods() : Flow<List<Food>> {
        val data = apiService.getAllFoods()
        return flowOf(data.data)
    }
}