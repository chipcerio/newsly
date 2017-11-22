package com.chipcerio.newsly.di.modules

import android.content.Context
import com.chipcerio.newsly.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val app: App) {

    @Provides
    @Singleton
    fun providesApplicationContext(): Context = app.applicationContext
}