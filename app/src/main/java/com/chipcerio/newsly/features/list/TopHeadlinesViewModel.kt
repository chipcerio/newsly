package com.chipcerio.newsly.features.list

import com.chipcerio.newsly.data.Article
import com.chipcerio.newsly.data.source.repository.TopHeadlinesRepository
import io.reactivex.Observable
import javax.inject.Inject


class TopHeadlinesViewModel @Inject
constructor(private val repository: TopHeadlinesRepository) {

    fun loadTopHeadlines(source: String): Observable<List<Article>> =
            repository.getTopHeadlines(source)
}
