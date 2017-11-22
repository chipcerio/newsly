package com.chipcerio.newsly.data

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "news")
data class News (
        @PrimaryKey
        val id: String,

        val title: String
)