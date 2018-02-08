package com.chipcerio.newsly.data.source.remote

import com.chipcerio.newsly.api.ApiService
import com.chipcerio.newsly.data.raw_types.Article
import com.chipcerio.newsly.data.raw_types.Source
import com.chipcerio.newsly.data.source.ArticleSource
import io.reactivex.Observable
import javax.inject.Inject

class ArticlesRemoteSource @Inject
constructor(private val apiService: ApiService) : ArticleSource {

    override fun getArticles(sources: List<String>, page: Int): Observable<List<Article>> {
        return apiService
            .getEverything(sources.joinToString(separator = ","), page)
            .map { it.articles }
    }

    override fun save(article: Article) {}

    override fun save(source: Source) {}
}

