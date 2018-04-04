package com.chipcerio.newsly.data.source.remote

import com.chipcerio.newsly.api.ApiService
import com.chipcerio.newsly.data.dto.Source
import com.chipcerio.newsly.data.source.NewsSources
import io.reactivex.Observable
import timber.log.Timber
import javax.inject.Inject

class NewsRemoteSources @Inject
constructor(private val apiService: ApiService) : NewsSources {

    override fun getSources(): Observable<List<Source>> {
        return apiService.getSources()
            .map {
                it.sources
            }
            .flatMapIterable {
                it
            }
            .map {
                Source(
                    id = it.id,
                    name = it.name,
                    description = it.description,
                    url = it.url,
                    category = it.category,
                    language = it.language,
                    country = it.country,
                    selected = false)
            }
            .doOnNext {
                Timber.d("$it")
            }
            .toList().toObservable()
            .onErrorReturn {
                emptyList()
            }
    }

    override fun saveSource(source: Source) {
    }
}