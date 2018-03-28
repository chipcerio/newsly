package com.chipcerio.newsly.features.sources_list

import com.chipcerio.newsly.api.ApiService
import com.chipcerio.newsly.data.AppDatabase
import com.chipcerio.newsly.data.source.NewsSources
import com.chipcerio.newsly.data.source.local.NewsLocalSources
import com.chipcerio.newsly.data.source.remote.NewsRemoteSources
import com.chipcerio.newsly.di.scopes.Local
import com.chipcerio.newsly.di.scopes.Remote
import dagger.Module
import dagger.Provides

@Module
class SourcesActivityModule {

    @Provides
    @Remote
    fun providesNewsRemoteSource(apiService: ApiService): NewsSources = NewsRemoteSources(apiService)

    @Provides
    @Local
    fun providesNewsLocalSource(db: AppDatabase): NewsSources = NewsLocalSources(db)

}