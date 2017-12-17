package com.chipcerio.newsly.features.list

import com.chipcerio.newsly.data.Article
import com.chipcerio.newsly.data.source.repository.ArticlesRepository
import io.reactivex.Observable
import javax.inject.Inject


class ArticlesViewModel @Inject
constructor(private val repository: ArticlesRepository) {

    fun loadArticles(sources: List<String>, page: Int): Observable<List<Article>> {
        return repository.getArticles(sources, page)
    }

}