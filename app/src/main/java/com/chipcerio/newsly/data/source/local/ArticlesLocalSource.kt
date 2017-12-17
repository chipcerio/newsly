package com.chipcerio.newsly.data.source.local

import com.chipcerio.newsly.data.Article
import com.chipcerio.newsly.data.source.ArticleSource
import io.reactivex.Observable
import javax.inject.Inject


class ArticlesLocalSource @Inject
constructor(private val db: AppDatabase) : ArticleSource {

    override fun getArticles(sources: List<String>, page: Int): Observable<List<Article>> =
            db.articlesDao().getArticles().toObservable()

    override fun save(article: Article) {
        db.articlesDao().save(article)
    }


}