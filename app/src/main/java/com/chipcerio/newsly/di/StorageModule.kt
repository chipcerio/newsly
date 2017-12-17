package com.chipcerio.newsly.di

import android.arch.persistence.room.Room
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.chipcerio.newsly.common.Constants.Database
import com.chipcerio.newsly.data.source.local.AppDatabase
import com.chipcerio.newsly.features.list.ArticlesActivityComponent
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(subcomponents = [(ArticlesActivityComponent::class)])
class StorageModule {

    @Provides @Singleton
    fun providesSharedPreferences(context: Context): SharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(context)

    @Provides @Singleton
    fun providesAppDatabase(context: Context): AppDatabase =
            Room.databaseBuilder(context, AppDatabase::class.java, Database.NAME).build()

}