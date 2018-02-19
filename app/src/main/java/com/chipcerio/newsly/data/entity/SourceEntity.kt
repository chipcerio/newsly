package com.chipcerio.newsly.data.entity

import android.annotation.SuppressLint
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import com.chipcerio.newsly.common.Constants.Database.Table
import kotlinx.android.parcel.Parcelize

// https://android.jlelse.eu/android-architecture-components-room-relationships-bf473510c14a
@Parcelize
@SuppressLint("ParcelCreator")
@Entity(tableName = Table.SOURCES)
data class SourceEntity(

    @PrimaryKey val id: String,

    val name: String

) : Parcelable {
    override fun toString(): String = "id:$id, name:$name"
}