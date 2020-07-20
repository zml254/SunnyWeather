package com.sunnyweather.android.bean

import com.google.gson.annotations.SerializedName
import java.util.*


data class DailyResponse(val status: String, val result: Result) {

    data class Result(val daily: Daily)

    data class Daily(
        val temperature: List<Temperature>,
        val skycon: List<Skycon>, @SerializedName("life_index") val lifeIndex: LifeIndex,
        val astro: List<Astro>,
        val wind: List<Wind>
    )

    data class Wind(val avg: WindAvg)

    data class WindAvg(val speed: Double, val direction: Double)

    data class Temperature(val max: Float, val min: Float)

    data class Skycon(val value: String, val date: Date)

    data class Astro(val sunset: Sunset, val sunrise: Sunrise)

    data class Sunset(val time: String)

    data class Sunrise(val time: String)

    data class LifeIndex(
        val coldRisk: List<LifeDescription>,
        val carWashing: List<LifeDescription>,
        val ultraviolet: List<LifeDescription>,
        val dressing: List<LifeDescription>
    )

    data class LifeDescription(val desc: String)



}