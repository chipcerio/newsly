package com.chipcerio.newsly.data

import android.annotation.SuppressLint
import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.ForeignKey.CASCADE
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import com.chipcerio.newsly.common.Constants.Database.Table
import kotlinx.android.parcel.Parcelize

// https://android.jlelse.eu/android-architecture-components-room-relationships-bf473510c14a
@Parcelize @SuppressLint("ParcelCreator")
@Entity(
    tableName = Table.SOURCES,
    foreignKeys = [ForeignKey(
            entity = Article::class,
            parentColumns = ["id"],
            childColumns = ["articleId"],
            onDelete = CASCADE)])
data class Source(

    @PrimaryKey
    val _id: Long,

    @ColumnInfo(name = "id")
    val sourceId: String?,

    val name: String,

    val articleId: Long

) : Parcelable {
    override fun toString(): String = "id:$sourceId, name:$name"
}