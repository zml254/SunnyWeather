package com.sunnyweather.android.bean

data class Weather(
    val realtime: RealtimeResponse.Realtime,
    val daily: DailyResponse.Daily,
    val hourly: HourlyResponse.Hourly
)