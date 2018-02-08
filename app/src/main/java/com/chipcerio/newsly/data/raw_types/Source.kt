package com.chipcerio.newsly.data.raw_types

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
class Source (val id: String, val name: String) : Parcelable