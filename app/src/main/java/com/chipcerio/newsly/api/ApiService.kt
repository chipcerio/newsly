package com.chipcerio.newsly.api

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    object Api {
        const val BASE_URL = "https://newsapi.org/"
        const val VERSION = "v2/"
    }

    @GET("everything")
    fun getEverything(
        @Query("sources") sources: String,
        @Query("page") page: Int
    ): Observable<ArticlesResponse>
}