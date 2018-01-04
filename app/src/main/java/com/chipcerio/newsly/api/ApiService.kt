package com.chipcerio.newsly.api

import com.chipcerio.newsly.api.response.TopHeadlinesResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    object Api {
        val BASE_URL = "https://newsapi.org/"
        val VERSION = "v2/"
    }

    @GET("top-headlines")
    fun getTopHeadlines(
        @Query("sources") source: String
    ): Observable<TopHeadlinesResponse>

    @GET("everything")
    fun getEverything(
        @Query("sources") sources: String,
        @Query("page") page: Int
    ): Observable<ArticlesResponse>
}