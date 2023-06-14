package com.rozi.dompetmakanan.data.remote.retrofit

import com.rozi.dompetmakanan.data.remote.response.*
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

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
    suspend fun getUserWithId(
        @Header("Authorization") token : String,
        @Path("id") id : Int
    ) : GetUserResponse

    @GET("foods")
    suspend fun getAllFoods(
        @Header("Authorization") token : String
    ) : FoodResponse

    @FormUrlEncoded
    @POST("recommendation/location")
    suspend fun getFoodByKategori(
        @Header("Authorization") token : String,
        @Field("kategori") kategori : String
    ) : FoodResponse

    @Multipart
    @POST("predict")
    suspend fun predictImage(
        @Part file : MultipartBody.Part
    ) : PredictResponse
}