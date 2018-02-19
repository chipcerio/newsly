package com.chipcerio.newsly.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.chipcerio.newsly.common.Constants.Database.VERSION
import com.chipcerio.newsly.data.dao.ArticlesDao
import com.chipcerio.newsly.data.dao.SourcesDao
import com.chipcerio.newsly.data.entity.ArticlesEntity
import com.chipcerio.newsly.data.entity.SourceEntity

@Database(
    entities = [ArticlesEntity::class, SourceEntity::class],
    version = VERSION,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun articlesDao(): ArticlesDao

    abstract fun sourcesDao(): SourcesDao
}