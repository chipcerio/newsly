package com.chipcerio.newsly.di

import com.chipcerio.newsly.App
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

// https://medium.com/@iammert/new-android-injector-with-dagger-2-part-1-8baa60152abe
// https://medium.com/@iammert/new-android-injector-with-dagger-2-part-2-4af05fd783d0
@Singleton
@Component(modules = [
    (AndroidInjectionModule::class),
    (AppModule::class),
    (NetworkModule::class),
    (StorageModule::class),
    (ActivityBuilder::class)])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: App): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)
}