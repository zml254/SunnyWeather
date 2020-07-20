package com.sunnyweather.android.dao

import android.content.Context
import androidx.core.content.edit
import com.google.gson.Gson
import com.sunnyweather.android.SunnyWeatherApplication
import com.sunnyweather.android.bean.Place

object PlaceDao {

    fun savePlace(place: Place) {
        sharedPreferences().edit{
            putString("place${getSavePlaceNumber() + 1}", Gson().toJson(place))
        }
        savePlaceNumber()
    }

    fun getSavePlace(): Place {
        val placeJson = sharedPreferences().getString("place${getSavePlaceNumber()}", "")
        return Gson().fromJson(placeJson, Place::class.java)
    }

    fun savePlaceNumber(){
        sharedPreferences().edit{
            putInt("placeNumber", getSavePlaceNumber() + 1)
        }
    }

    fun getSavePlaceNumber(): Int {
        return sharedPreferences().getInt("placeNumber", 0)
    }

    fun getAllPlaces(): ArrayList<Place> {
        val places = ArrayList<Place>()
        var i = getSavePlaceNumber()
        val sp = sharedPreferences()
        while (i > 0) {
            val str = sp.getString("place$i", "")
            val p = Gson().fromJson(str, Place::class.java)
            var j = 0
            var isTurn = false
            while (j < places.size) {
                if (p.name == places[j].name) {
                    isTurn = true
                    break
                }
                j++
            }
            if (!isTurn) {
                places.add(p)
            }
            i--
        }
        return places
    }

    fun isPlaceSaved() = sharedPreferences().contains("place${getSavePlaceNumber()}")

    private fun sharedPreferences() = SunnyWeatherApplication.context.getSharedPreferences(
        "sunny_weather",
        Context.MODE_PRIVATE
    )

}