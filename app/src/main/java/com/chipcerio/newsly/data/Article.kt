package com.chipcerio.newsly.data

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "articles")
data class Article (

        @PrimaryKey(autoGenerate = true)
        val id: Long,

        val author: String,

        val title: String,

        val description: String,

        val url: String,

        val urlToImage: String,

        val publishedAt: String
)

