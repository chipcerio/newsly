package com.chipcerio.newsly.data.source.repository

import com.chipcerio.newsly.data.Article
import com.chipcerio.newsly.data.source.TopHeadlinesSource
import com.chipcerio.newsly.di.scopes.Local
import com.chipcerio.newsly.di.scopes.Remote
import io.reactivex.Observable
import timber.log.Timber
import javax.inject.Inject


class TopHeadlinesRepository @Inject
constructor(@Remote private val remote: TopHeadlinesSource,
            @Local  private val local:  TopHeadlinesSource) : TopHeadlinesSource {

    private var cachedArticles: MutableMap<Long, Article> = LinkedHashMap()

    private var cacheIsDirty = false

    override fun getTopHeadlines(source: String): Observable<MutableList<Article>> {
        if (cachedArticles.isNotEmpty() && !cacheIsDirty) {
            return Observable.fromIterable(cachedArticles.values).toList().toObservable()
        }

        val remoteArticles = getAndSaveRemoteArticles(source)

        return if (cacheIsDirty) {
            remoteArticles
        } else {
            val localArticles = getAndCacheLocalArticles(source)
            Observable.concat(localArticles, remoteArticles)
                    .filter { it.isNotEmpty() }
                    .firstOrError()
                    .doOnSuccess { Timber.d("list: ${it.size}") }
                    .toObservable()
        }
    }

    private fun getAndCacheLocalArticles(source: String): Observable<MutableList<Article>> {
        return local.getTopHeadlines(source)
                .flatMap {
                    Observable.fromIterable(it).doOnNext {
                        cachedArticles.put(it.id, it)
                    }.toList().toObservable()
                }
    }

    private fun getAndSaveRemoteArticles(source: String): Observable<MutableList<Article>> {
        return remote.getTopHeadlines(source)
                .flatMap {
                    Observable.fromIterable(it).doOnNext {
                        local.save(it)
                        cachedArticles.put(it.id, it)
                    }.toList().toObservable()
                }
                .doOnComplete { cacheIsDirty = false }
    }

    override fun save(article: Article) {
        TODO("not implemented")
    }
}