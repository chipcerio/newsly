package com.chipcerio.newsly.data.source.remote

import com.chipcerio.newsly.api.ApiService
import com.chipcerio.newsly.data.Article
import com.chipcerio.newsly.data.source.TopHeadlinesSource
import io.reactivex.Observable
import javax.inject.Inject


class TopHeadlinesRemoteSource @Inject
constructor(private val apiService: ApiService): TopHeadlinesSource {

    override fun getTopHeadlines(source: String): Observable<List<Article>> =
            apiService.getTopHeadlines(source)
                    .map { it.articles }

    override fun save(article: Article) {
        TODO("not implemented")
    }
}