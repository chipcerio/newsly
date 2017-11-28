package com.chipcerio.newsly.data.source

import com.chipcerio.newsly.data.Article
import io.reactivex.Observable

interface TopHeadlinesSource {

    fun getTopHeadlines(source: String): Observable<List<Article>>

    fun save(article: Article)
}