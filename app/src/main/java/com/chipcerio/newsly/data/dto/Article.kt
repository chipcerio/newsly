package com.chipcerio.newsly.data.dto

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class Article(

    val id: Long,

    val source: Source,

    val author: String,

    val title: String,

    val description: String,

    val url: String,

    val urlToImage: String,

    val publishedAt: String

) : Parcelable