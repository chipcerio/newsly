package com.chipcerio.newsly.data.dto

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class Source(val id: String, val name: String) : Parcelable