package com.chipcerio.newsly.di

import android.content.Context
import com.chipcerio.newsly.App
import com.chipcerio.newsly.features.list.ArticlesActivityComponent
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(subcomponents = [(ArticlesActivityComponent::class)])
class AppModule {

    @Provides @Singleton
    fun providesApplicationContext(app: App): Context = app.applicationContext

}