package com.chipcerio.newsly.features.list

import com.chipcerio.newsly.api.ApiService
import com.chipcerio.newsly.data.source.EverythingSource
import com.chipcerio.newsly.data.source.local.AppDatabase
import com.chipcerio.newsly.data.source.local.EverythingLocalSource
import com.chipcerio.newsly.data.source.remote.EverythingRemoteSource
import com.chipcerio.newsly.di.scopes.Local
import com.chipcerio.newsly.di.scopes.Remote
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class EverythingRepositoryModule {

    @Provides
    @Singleton
    @Remote
    fun providesEverythingRemoteSource(apiService: ApiService): EverythingSource {
        return EverythingRemoteSource(apiService)
    }

    @Provides
    @Singleton
    @Local
    fun providesEverythingLocalSource(db: AppDatabase): EverythingSource {
        return EverythingLocalSource(db)

    }
}