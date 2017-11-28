package com.chipcerio.newsly.data.source.local

import com.chipcerio.newsly.data.Article
import com.chipcerio.newsly.data.source.EverythingSource
import io.reactivex.Observable
import javax.inject.Inject


class EverythingLocalSource @Inject
constructor(private val db: AppDatabase) : EverythingSource {

    override fun getArticles(sources: List<String>, page: Int): Observable<List<Article>> =
            db.articlesDao().getArticles().toObservable()

    override fun save(article: Article) {
        db.articlesDao().save(article)
    }


}