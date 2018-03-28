package com.chipcerio.newsly.features.sources_list

import com.chipcerio.newsly.data.dto.Source
import com.chipcerio.newsly.data.source.repository.SourcesRepository
import io.reactivex.Observable
import javax.inject.Inject

class SourcesViewModel @Inject
constructor(private val repository: SourcesRepository) {

    fun getSources(): Observable<List<Source>> {
        return repository.getSources()
    }
}