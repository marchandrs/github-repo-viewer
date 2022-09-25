package com.marchand.githubrepoviewer.repositories

import android.app.Application
import android.content.SharedPreferences
import com.marchand.githubrepoviewer.R

class SettingsRepository(var sharedPreferences: SharedPreferences) {

    fun saveUser(user: String) {
        sharedPreferences.edit().putString("user", user).apply()
    }

    fun getUser(): String {
        return sharedPreferences.getString("user", "appswefit")!!
    }
}