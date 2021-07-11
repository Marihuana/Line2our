package com.four.hackerton.line2our.global

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.google.gson.Gson

object SharedPreferenceHelper {
    private const val PREF_NAME = "kr.hackerton.four.line2our"
    private const val ACCESS_TOKEN = "ACCESS_TOKEN"

    private lateinit var sharedPreferences : SharedPreferences

    fun init(context: Context){
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Activity.MODE_PRIVATE)
    }

    private fun getPreference(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREF_NAME, Activity.MODE_PRIVATE)
    }

    var accessToken : String?
        get() = sharedPreferences.getString(ACCESS_TOKEN, "")
        set(value) = sharedPreferences.edit { putString(ACCESS_TOKEN, value) }
}