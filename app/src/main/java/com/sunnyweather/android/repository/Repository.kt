package com.sunnyweather.android.repository

import androidx.lifecycle.liveData
import com.sunnyweather.android.bean.Article
import com.sunnyweather.android.dao.PlaceDao
import com.sunnyweather.android.bean.Place
import com.sunnyweather.android.bean.Weather
import com.sunnyweather.android.network.ArticleNetwork
import com.sunnyweather.android.network.ArticleService
import com.sunnyweather.android.network.SunnyWeatherNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import java.lang.Exception
import java.lang.RuntimeException

object Repository {

    fun searchPlaces(query: String) = liveData(Dispatchers.IO) {

        val result = try {
            val placeResponse = SunnyWeatherNetwork.searchPlaces(query)
            if (placeResponse.status == "ok") {
                val places = placeResponse.places
                Result.success(places)
            } else {
                Result.failure(RuntimeException("Response status is ${placeResponse.status}"))
            }
        } catch (e: Exception) {
            Result.failure<List<Place>>(e)
        }
        emit(result)
    }

    suspend fun getNavi() = ArticleNetwork.getNaviResponse()

    suspend fun getArticleByWx(id: Int) = ArticleNetwork.getArticleByWx(id)

    suspend fun getWxArticle() = ArticleNetwork.getWxArticle()

    suspend fun getArticle(id: Int) = ArticleNetwork.getArticle(id)

    suspend fun getArticle() = ArticleNetwork.getArticle()

    suspend fun getBanner() = ArticleNetwork.getBanner()

    suspend fun getTree() = ArticleNetwork.getTree()

    fun getAllPlaces() = PlaceDao.getAllPlaces()

    fun savePlace(place: Place) = PlaceDao.savePlace(place)

    fun getSavedPlace() = PlaceDao.getSavePlace()

    fun isPlaceSaved() = PlaceDao.isPlaceSaved()

    fun refreshWeather(lng:String,lat:String) = liveData (Dispatchers.IO){

        val result = try {
            coroutineScope {
                val deferredRealtime = async {
                    SunnyWeatherNetwork.getRealtimeWeather(lng, lat)
                }
                val deferredDaily = async {
                    SunnyWeatherNetwork.getDailyWeather(lng, lat)
                }
                val deferredHourly = async {
                    SunnyWeatherNetwork.getHourlyResponse(lng, lat)
                }
                val realtimeResponse = deferredRealtime.await()
                val dailyResponse = deferredDaily.await()
                val hourlyResponse = deferredHourly.await()
                if (realtimeResponse.status == "ok"
                    && dailyResponse.status == "ok"
                    && hourlyResponse.status == "ok"
                ) {
                    val weather =
                        Weather(
                            realtimeResponse.result.realtime,
                            dailyResponse.result.daily,
                            hourlyResponse.result.hourly
                        )
                    Result.success(weather)
                } else {
                    Result.failure(
                        RuntimeException(
                            "realtime response status is ${realtimeResponse.status}" +
                                    "daily response status is ${dailyResponse.status}"
                        )
                    )
                }
            }
        } catch (e: Exception) {
            Result.failure<Weather>(e)
        }
        emit(result)
    }

}