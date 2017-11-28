package com.chipcerio.newsly.data.source.remote

import com.chipcerio.newsly.api.ApiService
import com.chipcerio.newsly.data.Article
import com.chipcerio.newsly.data.source.EverythingSource
import io.reactivex.Observable
import javax.inject.Inject


class EverythingRemoteSource @Inject
constructor(private val apiService: ApiService) : EverythingSource {

    override fun getArticles(sources: List<String>, page: Int): Observable<List<Article>> {
        return apiService.getEverything(sources.joinToString(separator = ","), page)
                .map { it.articles }
    }

    override fun save(article: Article) {
    }

}