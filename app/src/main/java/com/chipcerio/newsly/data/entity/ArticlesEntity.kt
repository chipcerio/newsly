package com.chipcerio.newsly.data.entity

import android.annotation.SuppressLint
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import com.chipcerio.newsly.common.Constants.Database.Table
import kotlinx.android.parcel.Parcelize

@Parcelize
@SuppressLint("ParcelCreator")
@Entity(tableName = Table.ARTICLES)
data class ArticlesEntity(

    @PrimaryKey
    val id: String,

    val source: String,

    val author: String,

    val title: String,

    val description: String,

    val url: String,

    val urlToImage: String,

    val publishedAt: String

) : Parcelable {
    override fun toString(): String = "id:$id, title:$title, publishedAt:$publishedAt"
}