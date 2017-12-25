package com.chipcerio.newsly.di

import com.chipcerio.newsly.features.list.ArticlesActivity
import com.chipcerio.newsly.features.list.ArticlesActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [(ArticlesActivityModule::class)])
    abstract fun bindArticlesActivity(): ArticlesActivity

    // add all activities here
}
