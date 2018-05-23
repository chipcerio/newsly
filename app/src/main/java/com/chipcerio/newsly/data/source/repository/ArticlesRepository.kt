package com.chipcerio.newsly.data.source.repository

import com.chipcerio.newsly.data.dto.Article
import com.chipcerio.newsly.data.dto.Source
import com.chipcerio.newsly.data.source.ArticleSource
import com.chipcerio.newsly.di.scopes.Local
import com.chipcerio.newsly.di.scopes.Remote
import io.reactivex.Observable
import javax.inject.Inject

class ArticlesRepository @Inject
constructor(
    @Remote private val remote: ArticleSource,
    @Local private val local: ArticleSource
) : ArticleSource {

    override fun getArticles(sources: List<String>, page: Int): Observable<List<Article>> {
        return remote.getArticles(sources, page)
            .flatMapIterable { it }
            .doOnNext {
                save(it)
                save(it.source)
            }.toList().toObservable()
    }

    override fun save(article: Article) {
        local.save(article)
    }

    override fun save(source: Source) {
        local.save(source)
    }
}