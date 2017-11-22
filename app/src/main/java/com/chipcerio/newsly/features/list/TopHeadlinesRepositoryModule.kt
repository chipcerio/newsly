package com.chipcerio.newsly.features.list

import com.chipcerio.newsly.api.ApiService
import com.chipcerio.newsly.data.source.TopHeadlinesSource
import com.chipcerio.newsly.data.source.local.AppDatabase
import com.chipcerio.newsly.data.source.local.TopHeadlinesLocalSource
import com.chipcerio.newsly.data.source.remote.TopHeadlinesRemoteSource
import com.chipcerio.newsly.di.scopes.Local
import com.chipcerio.newsly.di.scopes.Remote
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class TopHeadlinesRepositoryModule {


    @Provides
    @Singleton
    @Remote
    fun providesTopHeadlinesRemoteSource(apiService: ApiService): TopHeadlinesSource {
        return TopHeadlinesRemoteSource(apiService)
    }

    @Provides
    @Singleton
    @Local
    fun providesTopHeadlinesLocalSource(db: AppDatabase): TopHeadlinesSource {
        return TopHeadlinesLocalSource(db)
    }
}