package com.chipcerio.newsly.data.source.local

import com.chipcerio.newsly.data.AppDatabase
import com.chipcerio.newsly.data.dto.Source
import com.chipcerio.newsly.data.entity.SourceEntity
import com.chipcerio.newsly.data.source.NewsSources
import io.reactivex.Observable
import javax.inject.Inject

class NewsLocalSources @Inject
constructor(private val db: AppDatabase) : NewsSources {

    override fun getSources(): Observable<List<Source>> {
        return db.sourcesDao().getSources()
            .toObservable()
            .flatMapIterable { it }
            .map {
                Source(
                    id = it.id,
                    name = it.name,
                    description = "",
                    url = "",
                    category = "",
                    language = "",
                    country = "")
            }
            .toList()
            .toObservable()
    }

    override fun saveSource(source: Source) {
        val sourceEntity = SourceEntity(source.id, source.name)
        db.sourcesDao().save(sourceEntity)
    }
}