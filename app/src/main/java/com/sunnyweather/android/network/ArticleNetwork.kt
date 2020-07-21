package com.sunnyweather.android.network

import com.sunnyweather.android.bean.ArticleResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.RuntimeException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object ArticleNetwork{

    private val articleService = ArticleServiceCreator.create<ArticleService>()

    suspend fun getNaviResponse() = articleService.getNaviResponse().await()

    suspend fun getArticleByWx(id: Int) = articleService.getWxArticle(id).await()

    suspend fun getWxArticle() = articleService.getWxArticleResponse().await()

    suspend fun getArticle(id: Int) = articleService.getArticleResponse(id).await()

    suspend fun getArticle() = articleService.getArticleResponse().await()

    suspend fun getBanner() = articleService.getBannerResponse().await()

    suspend fun getTree() = articleService.getTreeResponse().await()

    private suspend fun <T> Call<T>.await(): T {
        return suspendCoroutine {continuation ->
            enqueue(object : Callback<T> {
                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }

                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    if (body != null) {
                        continuation.resume(body)
                    } else {
                        continuation.resumeWithException(
                            RuntimeException("response body is null")
                        )
                    }
                }
            })
        }
    }

}