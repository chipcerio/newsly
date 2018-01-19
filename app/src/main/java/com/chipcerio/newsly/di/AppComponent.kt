package com.chipcerio.newsly.di

import com.chipcerio.newsly.App
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

// https://medium.com/@iammert/new-android-injector-with-dagger-2-part-1-8baa60152abe
// https://medium.com/@iammert/new-android-injector-with-dagger-2-part-2-4af05fd783d0
// https://android.jlelse.eu/new-android-injector-with-dagger-2-part-3-fe3924df6a89
@Singleton
@Component(modules = [
    (AndroidSupportInjectionModule::class),
    (AppModule::class),
    (NetworkModule::class),
    (StorageModule::class),
    (ActivityBuilder::class)])
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<App>()
}