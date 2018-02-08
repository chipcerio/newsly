package com.chipcerio.newsly.data.source

import com.chipcerio.newsly.data.raw_types.Article
import com.chipcerio.newsly.data.raw_types.Source
import io.reactivex.Observable

interface ArticleSource {

    fun getArticles(sources: List<String>, page: Int): Observable<List<Article>>

    fun save(article: Article)

    fun save(source: Source)
}