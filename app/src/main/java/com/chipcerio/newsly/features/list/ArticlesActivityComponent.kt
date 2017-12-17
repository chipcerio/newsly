package com.chipcerio.newsly.features.list

import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent(modules = [(ArticlesActivityModule::class)])
interface ArticlesActivityComponent : AndroidInjector<ArticlesActivity> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<ArticlesActivity>()

}