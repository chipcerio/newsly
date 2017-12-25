package com.chipcerio.newsly.di

import android.content.Context
import com.chipcerio.newsly.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun providesApplicationContext(app: App): Context = app.applicationContext
}