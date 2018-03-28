package com.chipcerio.newsly.di

import com.chipcerio.newsly.features.list.ArticlesActivity
import com.chipcerio.newsly.features.list.ArticlesActivityModule
import com.chipcerio.newsly.features.sources_list.SourcesActivity
import com.chipcerio.newsly.features.sources_list.SourcesActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [(ArticlesActivityModule::class)])
    abstract fun bindArticlesActivity(): ArticlesActivity

    // add all activities here
    @ContributesAndroidInjector(modules = [SourcesActivityModule::class])
    abstract fun bindSourcesActivity(): SourcesActivity
}
