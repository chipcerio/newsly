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
                    description = it.description,
                    url = it.url,
                    category = it.category,
                    language = it.language,
                    country = it.country)
            }
            .toList()
            .toObservable()
    }

    override fun saveSource(source: Source) {
        val sourceEntity = SourceEntity(
            id = source.id,
            name = source.name,
            description = source.description,
            url = source.url,
            category = source.category,
            language = source.language,
            country = source.country)
        db.sourcesDao().save(sourceEntity)
    }
}