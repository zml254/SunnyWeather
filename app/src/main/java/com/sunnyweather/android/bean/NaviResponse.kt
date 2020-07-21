package com.sunnyweather.android.bean

data class NaviResponse(val data: List<Article>){

    data class Article(val articles: List<Title>, val name: String)

    data class Title(val link: String, val title: String)

}