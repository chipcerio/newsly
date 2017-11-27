package com.chipcerio.newsly.data.source.local

import com.chipcerio.newsly.data.Article
import com.chipcerio.newsly.data.source.TopHeadlinesSource
import io.reactivex.Observable
import javax.inject.Inject


class TopHeadlinesLocalSource @Inject
constructor(private val db: AppDatabase) : TopHeadlinesSource {

    override fun getTopHeadlines(source: String): Observable<MutableList<Article>> =
            db.articlesDao().getArticles().toObservable()

    override fun save(article: Article) {
        db.articlesDao().save(article)
    }
}