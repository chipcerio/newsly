package com.chipcerio.newsly.data.dto

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class Source (

    val id: String,

    val name: String,

    val description: String,

    val url: String,

    val category: String,

    val language: String,

    val country: String,

    var selected: Boolean = false

) : Parcelable