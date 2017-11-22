package com.chipcerio.newsly.data.source

import com.chipcerio.newsly.api.response.TopHeadlinesResponse
import io.reactivex.Observable


interface TopHeadlinesSource {

    fun getTopHeadlines(source: String): Observable<TopHeadlinesResponse>
}