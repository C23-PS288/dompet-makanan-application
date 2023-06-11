package com.rozi.dompetmakanan.data.remote.retrofit

import com.rozi.dompetmakanan.data.remote.response.FoodResponse
import com.rozi.dompetmakanan.data.remote.response.GetUserResponse
import com.rozi.dompetmakanan.data.remote.response.LoginResponse
import com.rozi.dompetmakanan.data.remote.response.RegisterResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @FormUrlEncoded
    @POST("users/register")
    fun register(
        @Field("name") name : String,
        @Field("email") email : String,
        @Field ("phone") phone: String,
        @Field("password") password : String,
        @Field("confirmPassword") confirmPassword : String
    ) : Call<RegisterResponse>

    @FormUrlEncoded
    @POST("users/login")
    fun login(
        @Field("email") email : String,
        @Field("password") password : String
    ) : Call<LoginResponse>

    @GET("users/{id}")
    fun getUserWithId(
        @Path("id") id : Int
    ) : Call<GetUserResponse>

    @GET("foods")
    suspend fun getAllFoods() : FoodResponse
}