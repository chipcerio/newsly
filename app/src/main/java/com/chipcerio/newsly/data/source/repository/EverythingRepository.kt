package com.chipcerio.newsly.data.source.repository

import com.chipcerio.newsly.data.Article
import com.chipcerio.newsly.data.source.EverythingSource
import com.chipcerio.newsly.di.scopes.Local
import com.chipcerio.newsly.di.scopes.Remote
import io.reactivex.Observable
import javax.inject.Inject


class EverythingRepository @Inject
constructor(@Remote private val remote: EverythingSource,
            @Local  private val  local: EverythingSource) : EverythingSource {

    override fun getArticles(sources: List<String>, page: Int): Observable<List<Article>> {
        return remote.getArticles(sources, page)
    }

    override fun save(article: Article) {
    }
}