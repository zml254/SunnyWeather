package com.sunnyweather.android.ui.weather

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sunnyweather.android.R
import com.sunnyweather.android.logic.model.HourlyResponse
import com.sunnyweather.android.logic.model.getSky

class HourlyAdapter(private val hourly: HourlyResponse.Hourly) :
    RecyclerView.Adapter<HourlyAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val skyIcon: ImageView = view.findViewById(R.id.iv_rv_item_skyIcon)
        val temperature: TextView = view.findViewById(R.id.tv_rv_item_temperature)
        val dateTime: TextView = view.findViewById(R.id.tv_rv_item_dateTime)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.include_weather_forecast_rv_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return hourly.temperature.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val temperature = hourly.temperature[position]
        val skycon = hourly.skycon[position]
        val date = temperature.datetime.substring(11, 16)
        holder.dateTime.text = date
        holder.skyIcon.setImageResource(getSky(skycon.value).icon)
        holder.temperature.text = "${temperature.value.toInt()} ℃"
    }

}