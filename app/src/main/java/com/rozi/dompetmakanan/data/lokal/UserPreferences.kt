package com.rozi.dompetmakanan.data.lokal

import android.content.Context

internal class UserPreferences(context: Context) {

    companion object {
        private const val USER = "user"
        private const val KEY = ""
    }

    private val preferences = context.getSharedPreferences(KEY, Context.MODE_PRIVATE)

    fun setUserId(id: Int) {
        val editor = preferences.edit()
        editor.putInt(USER, id)
        editor.apply()
    }

    fun getId(): Int {
        return preferences.getInt(USER, 0)
    }
}