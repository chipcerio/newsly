package com.chipcerio.newsly.di.components

import com.chipcerio.newsly.di.modules.AppModule
import com.chipcerio.newsly.di.modules.NetworkModule
import com.chipcerio.newsly.di.modules.StorageModule
import com.chipcerio.newsly.features.list.ArticlesActivity
import com.chipcerio.newsly.features.list.EverythingRepositoryModule
import com.chipcerio.newsly.features.list.TopHeadlinesRepositoryModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        AppModule::class,
        NetworkModule::class,
        StorageModule::class,
        TopHeadlinesRepositoryModule::class,
        EverythingRepositoryModule::class))
interface AppComponent {

    fun inject(activity: ArticlesActivity)

}