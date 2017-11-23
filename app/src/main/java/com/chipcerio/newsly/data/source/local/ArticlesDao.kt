package com.chipcerio.newsly.data.source.local

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.chipcerio.newsly.data.Article
import io.reactivex.Maybe

@Dao
interface ArticlesDao {

    @Query("SELECT * FROM articles")
    fun getArticles(): Maybe<Article>

}