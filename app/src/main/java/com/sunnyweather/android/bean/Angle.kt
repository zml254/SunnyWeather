package com.sunnyweather.android.bean

data class Angle(val angle: Float) {
    operator fun plus(fl: Float): Any {
        return angle + fl
    }
}