package com.sunnyweather.android.ui

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sunnyweather.android.MainActivity
import com.sunnyweather.android.R
import com.sunnyweather.android.SunnyWeatherApplication.Companion.context
import com.sunnyweather.android.bean.Weather
import com.sunnyweather.android.bean.getSky
import com.sunnyweather.android.ui.adapter.HourlyAdapter
import com.sunnyweather.android.viewmodel.WeatherViewModel
import kotlinx.android.synthetic.main.activity_weather.*
import kotlinx.android.synthetic.main.include_weathe_now.*
import kotlinx.android.synthetic.main.include_weather_forecast.*
import kotlinx.android.synthetic.main.include_weather_life_index.*
import kotlinx.android.synthetic.main.include_weather_sun.*
import kotlinx.android.synthetic.main.include_weather_wind.*
import java.text.SimpleDateFormat
import java.util.*


class WeatherActivity : AppCompatActivity() {

    val viewModel by lazy{ ViewModelProvider(this).get(WeatherViewModel::class.java)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val decorView = window.decorView
        decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        window.statusBarColor = Color.TRANSPARENT
        setContentView(R.layout.activity_weather)
        if (viewModel.locationLng.isEmpty()) {
            viewModel.locationLng = intent.getStringExtra("location_lng") ?: ""
        }
        if (viewModel.locationLat.isEmpty()) {
            viewModel.locationLat = intent.getStringExtra("location_lat") ?: ""
        }
        if (viewModel.placeName.isEmpty()) {
            viewModel.placeName = intent.getStringExtra("place_name") ?: ""
        }
        viewModel.weatherLiveDate.observe(this){ result ->
            val weather = result.getOrNull()
            if (weather != null) {
                swipeRefresh.setBackgroundColor(Color.WHITE)
                showWeatherInfo(weather)
            } else {
                Toast.makeText(this, "无法成功获取天气信息", Toast.LENGTH_SHORT).show()
                result.exceptionOrNull()?.printStackTrace()
                weatherLayout.visibility = View.GONE
                swipeRefresh.setBackgroundResource(R.drawable.bg_error)
            }
            swipeRefresh.isRefreshing = false
        }
        fab_weather_refresh.setOnClickListener {
            refreshWeather()
        }
        fab_weather_search.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("run", true)
            startActivity(intent)
        }
        swipeRefresh.setColorSchemeResources(R.color.black)
        refreshWeather()
        swipeRefresh.setOnRefreshListener {
            refreshWeather()
        }
        viewModel.refreshWeather(viewModel.locationLng, viewModel.locationLat)
    }

    fun refreshWeather() {
        weatherLayout.visibility = View.GONE
        viewModel.refreshWeather(viewModel.locationLng, viewModel.locationLat)
        swipeRefresh.isRefreshing = true
    }

    private fun showWeatherInfo(weather: Weather) {
        sunView.startAnimator(
            viewModel.getCurrentAngle(
                weather.daily.astro[0].sunrise.time,
                weather.daily.astro[0].sunset.time
            )
        )
        val animator =
            AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_fall_down)
        weatherLayout.layoutAnimation = animator
        weatherLayout.scheduleLayoutAnimation()
        tv_now_placeName.text = viewModel.placeName
        val realtime = weather.realtime
        val daily = weather.daily
        val astro = daily.astro
        val currentTempText = "${realtime.temperature.toInt()}℃"
        tv_now_currentTemp.text = currentTempText
        tv_now_currentSky.text = getSky(realtime.skycon).info
        val currentPM25Text = "空气指数${realtime.airQuality.aqi.chn.toInt()}"
        tv_now_currentAQI.text = currentPM25Text
        ll_forecast_layout.removeAllViews()
        nowLayout.setBackgroundResource(getSky(realtime.skycon).bg)
        val days = daily.skycon.size
        val line = LayoutInflater.from(this)
            .inflate(R.layout.include_weather_forecast_line, ll_forecast_layout, false)
        ll_forecast_layout.addView(line)
        val rv = (LayoutInflater.from(this)
            .inflate(
                R.layout.include_weather_forecast_rv,
                ll_forecast_layout,
                false
            )) as RecyclerView
        rv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rv.adapter =
            HourlyAdapter(weather.hourly)
        ll_forecast_layout.addView(rv)
        val line1 = LayoutInflater.from(this)
            .inflate(R.layout.include_weather_forecast_line, ll_forecast_layout, false)
        ll_forecast_layout.addView(line1)
        for (i in 0 until days) {
            val skycon = daily.skycon[i]
            val temperature = daily.temperature[i]
            val view = LayoutInflater.from(this).inflate(
                R.layout.include_weather_forecast_item,
                ll_forecast_layout,
                false
            )
            val dateInfo = view.findViewById<TextView>(R.id.tv_forecast_item_dataInfo)
            val skyIcon = view.findViewById<ImageView>(R.id.iv_forecast_item_skyIcon)
            val skyInfo = view.findViewById<TextView>(R.id.tv_forecast_item_skyInfo)
            val temperatureInfo = view.findViewById<TextView>(R.id.tv_forecast_item_tempInfo)
            val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            dateInfo.text = simpleDateFormat.format(skycon.date)
            val sky = getSky(skycon.value)
            skyIcon.setImageResource(sky.icon)
            skyInfo.text = sky.info
            val tempText = "${temperature.min.toInt()} ~ ${temperature.max.toInt()} ℃"
            temperatureInfo.text = tempText
            ll_forecast_layout.addView(view)
            val line2 = LayoutInflater.from(this)
                .inflate(R.layout.include_weather_forecast_line, ll_forecast_layout, false)
            ll_forecast_layout.addView(line2)
        }
        tv_wind_size_value.text = viewModel.getWindSpeed(daily.wind[0].avg.speed)
        tv_wind_direction_value.text = viewModel.getWindDirection(daily.wind[0].avg.direction)
        val lifeIndex = daily.lifeIndex
        tv_life_index_coldRisk.text = lifeIndex.coldRisk[0].desc
        tv_life_index_dressing.text = lifeIndex.dressing[0].desc
        tv_life_index_ultravioletText.text = lifeIndex.ultraviolet[0].desc
        tv_life_index_carWashing.text = lifeIndex.carWashing[0].desc
        tv_sun_sunRise.text = astro[0].sunrise.time
        tv_sun_sunSet.text = astro[0].sunset.time
        weatherLayout.visibility = View.VISIBLE
    }

    override fun onResume() {
        super.onResume()
        refreshWeather()
    }
}