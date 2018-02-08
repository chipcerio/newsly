package com.chipcerio.newsly.data.source.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.chipcerio.newsly.common.Constants.Database.VERSION
import com.chipcerio.newsly.data.ArticleModel
import com.chipcerio.newsly.data.SourceModel

@Database(entities = [(ArticleModel::class), (SourceModel::class)],
    version = VERSION,
    exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun articlesDao(): ArticlesDao

    abstract fun sourcesDao(): SourcesDao
}