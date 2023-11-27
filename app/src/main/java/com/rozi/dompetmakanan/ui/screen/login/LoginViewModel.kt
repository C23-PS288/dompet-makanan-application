package com.rozi.dompetmakanan.ui.screen.login

import android.app.Application
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rozi.dompetmakanan.data.lokal.TokenPreferences
import com.rozi.dompetmakanan.data.lokal.UserPreferences
import com.rozi.dompetmakanan.data.remote.response.LoginResponse
import com.rozi.dompetmakanan.data.remote.retrofit.ApiConfig
import com.rozi.dompetmakanan.model.Food
import com.rozi.dompetmakanan.utils.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel(application: Application) : ViewModel() {
//    val isSuccessLoading = mutableStateOf(value = false)
    val progressBar = mutableStateOf(value = false)
    val snackbarError = mutableStateOf(value = false)
    private val loginRequestLiveData = MutableLiveData<Boolean>()
    private val tokenPreferences = TokenPreferences(application)
    private val userPreferences = UserPreferences(application)

    private val _authUIState : MutableStateFlow<AuthUIState> = MutableStateFlow(AuthUIState.OnNotLogin)
    val authUIState: StateFlow<AuthUIState> get() = _authUIState

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
                            _authUIState.value =AuthUIState.OnLogin
                            response.body()?.let {
                                tokenPreferences.setToken(it.data.accessToken?:"")
                                userPreferences.setUserId(it.data.id?:0)
                                Log.d("Token", "Token ${it.data.accessToken}")
                            }
                        }else{
                            snackbarError.value = true
                        }
                        loginRequestLiveData.postValue(response.isSuccessful)
                        progressBar.value = false
                    }

                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        snackbarError.value = true
                        Log.d("Logging", t.message!!)
                        progressBar.value = false
                    }
                })
            } catch (e: Exception) {
                Log.d("Logging", "Error Authentication", e)
                snackbarError.value = true
                progressBar.value = false
            }
        }
    }
}