package com.sunnyweather.android.bean

import com.google.gson.annotations.SerializedName

data class HourlyResponse(val status:String,val result :Result){

    data class Result(val hourly: Hourly, @SerializedName("forecast_keypoint") val description: String)

    data class Hourly(val temperature: List<Temperature>, val skycon: List<Skycon>)

    data class Skycon(val datetime: String, val value: String)

    data class Temperature(val datetime: String, val value: Float)
}