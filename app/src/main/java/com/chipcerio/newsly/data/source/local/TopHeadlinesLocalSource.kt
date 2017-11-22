package com.chipcerio.newsly.data.source.local

import com.chipcerio.newsly.api.response.TopHeadlinesResponse
import com.chipcerio.newsly.data.source.TopHeadlinesSource
import io.reactivex.Observable
import javax.inject.Inject


class TopHeadlinesLocalSource @Inject
constructor(private val db: AppDatabase) : TopHeadlinesSource {

    override fun getTopHeadlines(source: String): Observable<TopHeadlinesResponse> {
        // db.newsDao().getNews()
        TODO("not implemented")
    }
}