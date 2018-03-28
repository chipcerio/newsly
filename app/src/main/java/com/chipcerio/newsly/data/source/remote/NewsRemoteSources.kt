package com.chipcerio.newsly.data.source.remote

import com.chipcerio.newsly.api.ApiService
import com.chipcerio.newsly.data.dto.Source
import com.chipcerio.newsly.data.source.NewsSources
import io.reactivex.Observable
import javax.inject.Inject

class NewsRemoteSources @Inject
constructor(private val apiService: ApiService) : NewsSources {

    override fun getSources(): Observable<List<Source>> =
        apiService.getSources()
            .map {
                it.sources
            }
}