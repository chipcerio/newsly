package com.chipcerio.newsly.data.source.local

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import com.chipcerio.newsly.common.Constants.Database.LIMIT
import com.chipcerio.newsly.common.Constants.Database.Table
import com.chipcerio.newsly.data.ArticleModel
import io.reactivex.Maybe

@Dao
interface ArticlesDao {

    @Query("SELECT * FROM ${Table.ARTICLES}")
    fun getArticles(): Maybe<List<ArticleModel>>

    /*
    SELECT * FROM articles
    WHERE sourceId IN ('bloomberg','bbc-news')
     */

    @Query("SELECT * FROM ${Table.ARTICLES} LIMIT $LIMIT OFFSET :offset")
    fun getArticlesByPage(offset: Int): Maybe<List<ArticleModel>>

    @Insert(onConflict = REPLACE)
    fun save(article: ArticleModel)
}