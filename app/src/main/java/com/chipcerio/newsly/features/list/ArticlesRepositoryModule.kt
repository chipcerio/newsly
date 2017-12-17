package com.chipcerio.newsly.features.list

import com.chipcerio.newsly.api.ApiService
import com.chipcerio.newsly.data.source.ArticleSource
import com.chipcerio.newsly.data.source.local.AppDatabase
import com.chipcerio.newsly.data.source.local.ArticlesLocalSource
import com.chipcerio.newsly.data.source.remote.ArticlesRemoteSource
import com.chipcerio.newsly.di.scopes.Local
import com.chipcerio.newsly.di.scopes.Remote
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ArticlesRepositoryModule {

    @Provides
    @Singleton
    @Remote
    fun providesArticlesRemoteSource(apiService: ApiService): ArticleSource {
        return ArticlesRemoteSource(apiService)
    }

    @Provides
    @Singleton
    @Local
    fun providesArticlesLocalSource(db: AppDatabase): ArticleSource {
        return ArticlesLocalSource(db)
    }
}