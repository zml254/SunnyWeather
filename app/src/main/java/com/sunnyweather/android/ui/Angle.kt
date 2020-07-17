package com.sunnyweather.android.ui

data class Angle(val angle: Float) {
    operator fun plus(fl: Float): Any {
        return angle + fl
    }
}