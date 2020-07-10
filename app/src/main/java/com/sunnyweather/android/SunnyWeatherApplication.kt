package com.sunnyweather.android

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class SunnyWeatherApplication : Application() {

    companion object{
        @SuppressLint
        lateinit var context: Context
        const val TOKEN = "KC7ZYqR6ATiR7czn"
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

}