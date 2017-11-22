package com.chipcerio.newsly.di.components

import com.chipcerio.newsly.di.modules.AppModule
import com.chipcerio.newsly.di.modules.NetworkModule
import com.chipcerio.newsly.di.modules.StorageModule
import com.chipcerio.newsly.features.list.MainActivity
import com.chipcerio.newsly.features.list.TopHeadlinesRepositoryModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        AppModule::class,
        NetworkModule::class,
        StorageModule::class,
        TopHeadlinesRepositoryModule::class))
interface AppComponent {

    fun inject(activity: MainActivity)

}