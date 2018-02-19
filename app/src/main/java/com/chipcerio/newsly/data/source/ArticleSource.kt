package com.chipcerio.newsly.data.source

import com.chipcerio.newsly.data.dto.Article
import com.chipcerio.newsly.data.dto.Source
import io.reactivex.Observable

interface ArticleSource {

    fun getArticles(sources: List<String>, page: Int): Observable<List<Article>>

    fun save(article: Article)

    fun save(source: Source)
}