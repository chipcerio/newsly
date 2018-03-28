package com.chipcerio.newsly.data.source.repository

import com.chipcerio.newsly.data.dto.Source
import com.chipcerio.newsly.data.source.NewsSources
import com.chipcerio.newsly.di.scopes.Local
import com.chipcerio.newsly.di.scopes.Remote
import io.reactivex.Observable
import javax.inject.Inject

class SourcesRepository @Inject
constructor(
    @Remote private val remote: NewsSources,
    @Local private val local: NewsSources
) : NewsSources {

    override fun getSources(): Observable<List<Source>> {
        return remote.getSources()
    }
}