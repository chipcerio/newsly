package com.chipcerio.newsly.data.source.remote

import com.chipcerio.newsly.api.ApiService
import com.chipcerio.newsly.api.response.TopHeadlinesResponse
import com.chipcerio.newsly.data.source.TopHeadlinesSource
import io.reactivex.Observable
import timber.log.Timber
import javax.inject.Inject


class TopHeadlinesRemoteSource @Inject
constructor(private val apiService: ApiService): TopHeadlinesSource {

    override fun getTopHeadlines(source: String): Observable<TopHeadlinesResponse> =
            apiService.getTopHeadlines(source)
}