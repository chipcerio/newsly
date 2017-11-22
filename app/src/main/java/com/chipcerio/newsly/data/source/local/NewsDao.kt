package com.chipcerio.newsly.data.source.local

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.chipcerio.newsly.data.News
import io.reactivex.Maybe

@Dao
interface NewsDao {

    @Query("SELECT * FROM news")
    fun getNews(): Maybe<News>
}