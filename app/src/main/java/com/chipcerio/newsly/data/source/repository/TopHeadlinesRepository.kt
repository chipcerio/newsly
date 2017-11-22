package com.chipcerio.newsly.data.source.repository

import com.chipcerio.newsly.api.response.TopHeadlinesResponse
import com.chipcerio.newsly.data.source.TopHeadlinesSource
import com.chipcerio.newsly.di.scopes.Local
import com.chipcerio.newsly.di.scopes.Remote
import io.reactivex.Observable
import javax.inject.Inject


class TopHeadlinesRepository @Inject
constructor(@Remote private val remote: TopHeadlinesSource,
            @Local  private val local:  TopHeadlinesSource) : TopHeadlinesSource {

    override fun getTopHeadlines(source: String): Observable<TopHeadlinesResponse> {
        // TODO save to local
        return remote.getTopHeadlines(source)
    }

}