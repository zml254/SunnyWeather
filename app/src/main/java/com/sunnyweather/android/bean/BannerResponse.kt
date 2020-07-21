package com.sunnyweather.android.bean

data class BannerResponse(val data: List<Banner>)

data class Banner(val imagePath: String, val url: String)