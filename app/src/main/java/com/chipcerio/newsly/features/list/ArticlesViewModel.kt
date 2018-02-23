package com.chipcerio.newsly.features.list

import com.chipcerio.newsly.data.dto.Article
import com.chipcerio.newsly.data.source.repository.ArticlesRepository
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class ArticlesViewModel @Inject
constructor(private val repository: ArticlesRepository) {

    private var onProgressStream = BehaviorSubject.createDefault(false)

    fun loadArticles(sources: List<String>, page: Int): Observable<List<Article>> {
        return repository.getArticles(sources, page)
            .doOnSubscribe {
                onProgressStream.onNext(true)
            }
            .doOnNext {
                onProgressStream.onNext(false)
            }
            .doOnComplete {
                onProgressStream.onNext(false)
            }
    }

    fun getLoadingIndicator(): Observable<Boolean> = onProgressStream
}