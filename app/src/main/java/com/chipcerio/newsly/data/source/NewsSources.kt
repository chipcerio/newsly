package com.chipcerio.newsly.data.source

import com.chipcerio.newsly.data.dto.Source
import io.reactivex.Observable

interface NewsSources {

    fun getSources(): Observable<List<Source>>

    fun saveSource(source: Source)
}