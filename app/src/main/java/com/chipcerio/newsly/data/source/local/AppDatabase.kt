package com.chipcerio.newsly.data.source.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.chipcerio.newsly.common.Constants.Database.VERSION
import com.chipcerio.newsly.data.Article

@Database(entities = [(Article::class)],
          version = VERSION,
          exportSchema = true)
abstract class AppDatabase : RoomDatabase() {

    abstract fun articlesDao(): ArticlesDao
}