package com.chipcerio.newsly.data.source.repository

import com.chipcerio.newsly.data.Article
import com.chipcerio.newsly.data.source.ArticleSource
import com.chipcerio.newsly.di.scopes.Local
import com.chipcerio.newsly.di.scopes.Remote
import io.reactivex.Observable
import javax.inject.Inject

class ArticlesRepository @Inject
constructor(@Remote private val remote: ArticleSource, @Local private val local: ArticleSource) : ArticleSource {

    private var cachedArticles: MutableMap<Long, Article> = LinkedHashMap()
    private var cacheIsDirty = false

    override fun getArticles(sources: List<String>, page: Int): Observable<List<Article>> {
        if (cachedArticles.isNotEmpty() and !cacheIsDirty) {
            return Observable.fromIterable(cachedArticles.values).toList().toObservable()
        }

        val remoteArticles = getAndSaveRemoteArticles(sources, page)

        return if (cacheIsDirty) remoteArticles else {
            val localArticles = getAndCacheLocalArticles(sources, page)
            Observable.concat(localArticles, remoteArticles)
                .filter { it.isNotEmpty() }
                .firstOrError()
                .toObservable()
        }
    }

    private fun getAndCacheLocalArticles(sources: List<String>, page: Int): Observable<List<Article>> {
        return local.getArticles(sources, page)
            .flatMap {
                Observable.fromIterable(it).doOnNext {
                    cachedArticles.put(it.id, it)
                }.toList().toObservable()
            }
    }

    private fun getAndSaveRemoteArticles(sources: List<String>, page: Int): Observable<List<Article>> {
        return remote.getArticles(sources, page)
            .flatMap {
                Observable.fromIterable(it).doOnNext {
                    save(it)
                }.toList().toObservable()
            }
            .doOnComplete { cacheIsDirty = false }
    }

    override fun save(article: Article) {
        local.save(article)
        cachedArticles.put(article.id, article)
    }
}