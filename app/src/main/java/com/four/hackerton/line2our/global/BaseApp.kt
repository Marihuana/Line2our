package com.four.hackerton.line2our.global

import android.app.Application

class BaseApp : Application() {

    override fun onCreate() {
        super.onCreate()

        SharedPreferenceHelper.init(applicationContext)
    }
}