package com.chipcerio.newsly.features.list

import com.chipcerio.newsly.data.Article
import com.chipcerio.newsly.data.source.repository.EverythingRepository
import io.reactivex.Observable
import javax.inject.Inject


class EverythingViewModel @Inject
constructor(private val repository: EverythingRepository) {

    fun loadArticles(sources: List<String>, page: Int): Observable<List<Article>> {
        return repository.getArticles(sources, page)
    }

}