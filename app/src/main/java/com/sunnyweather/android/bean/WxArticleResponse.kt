package com.sunnyweather.android.bean

data class WxArticleResponse(val data:List<Wx>){

    data class Wx(val name: String, val id: Int)

}