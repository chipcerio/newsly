package com.chipcerio.newsly

import android.app.Application
import com.chipcerio.newsly.di.components.AppComponent
import com.chipcerio.newsly.di.components.DaggerAppComponent
import com.chipcerio.newsly.di.modules.AppModule
import com.chipcerio.newsly.di.modules.NetworkModule
import com.chipcerio.newsly.di.modules.StorageModule
import com.jakewharton.threetenabp.AndroidThreeTen
import timber.log.Timber

class App : Application() {

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = with(DaggerAppComponent.builder()) {
            appModule(AppModule(this@App))
            networkModule(NetworkModule())
            storageModule(StorageModule())
            build()
        }

        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())

        AndroidThreeTen.init(this)
    }

    fun appComponent(): AppComponent = appComponent
}