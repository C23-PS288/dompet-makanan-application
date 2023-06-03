package com.rozi.dompetmakanan.ui.screen.login

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rozi.dompetmakanan.data.lokal.TokenPreferences
import com.rozi.dompetmakanan.data.remote.response.LoginResponse
import com.rozi.dompetmakanan.data.remote.retrofit.ApiConfig
import com.rozi.dompetmakanan.model.User
import com.rozi.dompetmakanan.utils.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel(application: Application) : ViewModel() {
    val isSuccessLoading = mutableStateOf(value = false)
    val progressBar = mutableStateOf(value = false)
    private val loginRequestLiveData = MutableLiveData<Boolean>()
    private val preferences = TokenPreferences(application)

    fun login(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                progressBar.value = true
                val client = ApiConfig.getApiService().login(email, password)
                client.enqueue(object : Callback<LoginResponse> {
                    override fun onResponse(
                        call: Call<LoginResponse>,
                        response: Response<LoginResponse>
                    ) {
                        if (response.isSuccessful) {
                            isSuccessLoading.value = true
                            response.body()?.let {
                                preferences.setToken(it.data.accessToken)
                                Log.d("Token", "Token ${it.data.accessToken}")
                            }
                        }
                        loginRequestLiveData.postValue(response.isSuccessful)
                        progressBar.value = false
                    }

                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        Log.d("Logging", t.message!!)
                        progressBar.value = false
                    }
                })
            } catch (e: Exception) {
                Log.d("Logging", "Error Authentication", e)
                progressBar.value = false
            }
        }
    }
}