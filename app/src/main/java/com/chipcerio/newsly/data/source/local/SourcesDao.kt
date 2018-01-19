package com.chipcerio.newsly.data.source.local

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import com.chipcerio.newsly.common.Constants.Database.Table
import com.chipcerio.newsly.data.SourceModel
import io.reactivex.Maybe

@Dao
interface SourcesDao {

    @Query("SELECT * FROM ${Table.SOURCES}")
    fun getSources(): Maybe<List<SourceModel>>

    @Query("SELECT * FROM ${Table.SOURCES} WHERE id = :id")
    fun getSource(id: String): Maybe<SourceModel>

    @Insert(onConflict = REPLACE)
    fun save(source: SourceModel)
}