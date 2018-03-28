package com.chipcerio.newsly.data.source.local

import com.chipcerio.newsly.data.AppDatabase
import com.chipcerio.newsly.data.dto.Source
import com.chipcerio.newsly.data.source.NewsSources
import io.reactivex.Observable
import javax.inject.Inject

class NewsLocalSources @Inject
constructor(private val db: AppDatabase) : NewsSources {

    override fun getSources(): Observable<List<Source>> {
        TODO()
    }
}