package com.chipcerio.newsly.data.source

import com.chipcerio.newsly.data.Article
import io.reactivex.Observable

interface ArticleSource {

    fun getArticles(sources: List<String>, page: Int): Observable<List<Article>>

    fun save(article: Article)
}