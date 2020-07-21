package com.sunnyweather.android.network

import com.sunnyweather.android.bean.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ArticleService {
    @GET("article/list/0/json")
    fun getArticleResponse(): Call<ArticleResponse>

    @GET("banner/json")
    fun getBannerResponse(): Call<BannerResponse>

    @GET("tree/json")
    fun getTreeResponse(): Call<TreeResponse>

    @GET("article/list/0/json")
    fun getArticleResponse(@Query("cip") id: Int): Call<ArticleResponse>

    @GET("wxarticle/chapters/json")
    fun getWxArticleResponse(): Call<WxArticleResponse>

    @GET("wxarticle/list/{id}/1/json")
    fun getWxArticle(@Path("id") id: Int): Call<ArticleResponse>

    @GET("navi/json")
    fun getNaviResponse(): Call<NaviResponse>

}