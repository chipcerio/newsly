package com.chipcerio.newsly.features.list

import com.chipcerio.newsly.data.raw_types.Article
import com.chipcerio.newsly.data.source.repository.ArticlesRepository
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class ArticlesViewModel @Inject
constructor(private val repository: ArticlesRepository) {

    private var loadingIndicatorSubject = BehaviorSubject.createDefault(false)

    fun loadArticles(sources: List<String>, page: Int): Observable<List<Article>> {
        return repository.getArticles(sources, page)
            .doOnSubscribe { loadingIndicatorSubject.onNext(true) }
            .doOnNext { loadingIndicatorSubject.onNext(false) }
    }

    fun getLoadingIndicator(): Observable<Boolean> = loadingIndicatorSubject
}