package com.sunnyweather.android.viewmodel

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
            currentTime.substring(0, 2)
        ) * 60 + Integer.parseInt(sunrise.substring(3, 5))
        val set = Integer.parseInt(
            currentTime.substring(0, 2)
        ) * 60 + Integer.parseInt(sunset.substring(3, 5))
        return if (current > set) {
            180f
        }else if (current < rise) {
            0f
        } else {
            (current - rise) / (set - rise) * 180f
        }

    }

}