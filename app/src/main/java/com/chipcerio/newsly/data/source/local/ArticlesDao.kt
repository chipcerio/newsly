package com.chipcerio.newsly.data.source.local

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import com.chipcerio.newsly.common.Constants.Database.Table
import com.chipcerio.newsly.data.Article
import io.reactivex.Maybe

@Dao
interface ArticlesDao {

    @Query("SELECT * FROM ${Table.ARTICLES}")
    fun getArticles(): Maybe<List<Article>>

    @Insert(onConflict = REPLACE)
    fun save(article: Article)
}