package com.sunnyweather.android.bean

data class ArticleResponse (val data:Data, val errorCode: Int)

data class Data(val datas: List<Article>)

data class Article(
    val chapterName: String,
    val link: String,
    val niceDate: String,
    val author: String,
    val title: String,
    val shareUser: String,
    val zan: Int
)