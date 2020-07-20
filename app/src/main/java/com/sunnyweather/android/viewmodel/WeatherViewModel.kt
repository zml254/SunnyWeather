package com.sunnyweather.android.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.sunnyweather.android.repository.Repository
import com.sunnyweather.android.bean.Location
import java.text.SimpleDateFormat
import java.util.*

class WeatherViewModel : ViewModel() {

    private val locationLiveData = MutableLiveData<Location>()

    var locationLng = ""

    var locationLat = ""

    var placeName = ""

    val weatherLiveDate = Transformations.switchMap(locationLiveData){location ->
        Repository.refreshWeather(location.lng, location.lat)
    }

    fun refreshWeather(lng: String, lat: String) {
        locationLiveData.value = Location(lng, lat)
    }

    fun getCurrentAngle(sunrise: String, sunset: String):Float {
        val day = Date()
        val df = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val currentTime = df.format(day)
        val current = Integer.parseInt(
            currentTime.substring(11, 13)
        ) * 60 + Integer.parseInt(currentTime.substring(14, 16))
        val rise = Integer.parseInt(
            sunrise.substring(0, 2)
        ) * 60 + Integer.parseInt(sunrise.substring(3, 5))
        val set = Integer.parseInt(
            sunset.substring(0, 2)
        ) * 60 + Integer.parseInt(sunset.substring(3, 5))
        Log.d("getCurrentAngle: ", "${rise}+$current+$set")
        return if (current > set) {
            180f
        }else if (current < rise) {
            0f
        } else {
            (current - rise).toFloat() / (set - rise) * 180f
        }

    }

    fun getWindSpeed(speed: Double): String {

        return when (speed) {
            in 0.0..0.3 -> "无风"
            in 0.3..1.5 -> "软风"
            in 1.6..3.3 -> "轻风"
            in 3.4..5.4 -> "微风"
            in 5.5..7.9 -> "和风"
            in 8.0..10.7 -> "劲风"
            in 10.8..13.8 -> "强风"
            in 13.9..17.1 -> "疾风"
            in 17.2..20.7 -> "大风"
            in 20.8..24.4 -> "烈风"
            in 24.5..28.4 -> "狂风"
            in 28.5..32.6 -> "暴风"
            else -> "飓风"
        }
    }

    fun getWindDirection(speed: Double): String {

        return when (speed) {
            in 22.6..67.5 -> "东北风"
            in 67.6..112.5 -> "东风"
            in 112.6..157.5 -> "东南"
            in 157.6..202.5 -> "南"
            in 202.6..247.5 -> "西南"
            in 247.6..295.5 -> "西"
            in 295.6..337.5 -> "西北"
            else -> "北风"
        }

    }

}