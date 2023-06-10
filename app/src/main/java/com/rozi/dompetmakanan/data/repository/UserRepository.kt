package com.rozi.dompetmakanan.data.repository

import android.content.Context
import android.util.Log
import com.rozi.dompetmakanan.data.lokal.UserPreferences
import com.rozi.dompetmakanan.data.remote.response.GetUserResponse
import com.rozi.dompetmakanan.data.remote.retrofit.ApiService
import com.rozi.dompetmakanan.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository(private val apiService : ApiService, context: Context) {

    private val preferences = UserPreferences(context)

    fun getUserWithId(): User {
        var user : User = User()
        val client = apiService.getUserWithId(preferences.getId())
        client.enqueue(object : Callback<GetUserResponse>{
            override fun onResponse(
                call: Call<GetUserResponse>,
                response: Response<GetUserResponse>
            ) {
                if(response.isSuccessful){
                    val data = response.body()?.data
                    user = data!!
                    Log.d("Nama",data.name!!)
                }
            }

            override fun onFailure(call: Call<GetUserResponse>, t: Throwable) {
                Log.d("Error", t.message!!)
            }
        })
        return user
    }
}