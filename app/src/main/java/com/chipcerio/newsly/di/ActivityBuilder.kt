package com.chipcerio.newsly.di

import android.app.Activity
import com.chipcerio.newsly.features.list.ArticlesActivity
import com.chipcerio.newsly.features.list.ArticlesActivityComponent
import dagger.Binds
import dagger.Module
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class ActivityBuilder {

    @Binds @IntoMap
    @ActivityKey(ArticlesActivity::class)
    abstract fun bindArticlesActivity(builder: ArticlesActivityComponent.Builder): AndroidInjector.Factory<out Activity>

    // add all activities here

}