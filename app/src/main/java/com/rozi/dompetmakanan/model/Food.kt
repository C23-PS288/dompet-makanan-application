package com.rozi.dompetmakanan.model

import com.google.gson.annotations.SerializedName

data class Food(
    @field:SerializedName("menu_pic")
    val menuPic: String,
    val harga: Int? = null,
    @field:SerializedName("nama_warung")
    val namaWarung: String? = null,
    val kategori: String? = null,
    val id: Int,
    val menu: String? = null
)
