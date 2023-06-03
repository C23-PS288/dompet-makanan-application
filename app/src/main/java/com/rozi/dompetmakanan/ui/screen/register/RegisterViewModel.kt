package com.rozi.dompetmakanan.ui.screen.register

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rozi.dompetmakanan.data.remote.response.RegisterResponse
import com.rozi.dompetmakanan.data.remote.retrofit.ApiConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterViewModel : ViewModel() {
    val isSuccessLoading = mutableStateOf(value = false)
    val progressBar = mutableStateOf(value = false)
    private val registerRequestLiveData = MutableLiveData<Boolean>()

    fun register(name: String, email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                progressBar.value = true
                val client = ApiConfig.getApiService().register(name, email, password)
                client.enqueue(object : Callback<RegisterResponse> {
                    override fun onResponse(
                        call: Call<RegisterResponse>,
                        response: Response<RegisterResponse>
                    ) {
                        if (response.isSuccessful) {
                            isSuccessLoading.value = true
                        }
                        registerRequestLiveData.postValue(response.isSuccessful)
                        progressBar.value = false
                    }

                    override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                        Log.d("Registering", t.message!!)
                    }
                })
            } catch (e: Exception) {
                Log.d("Registering", "Error Authentication", e)
                progressBar.value = false
            }
        }
    }
}