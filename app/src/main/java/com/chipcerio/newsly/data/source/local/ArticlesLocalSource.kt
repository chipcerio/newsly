package com.chipcerio.newsly.data.source.local

import com.chipcerio.newsly.common.Constants.Database.LIMIT
import com.chipcerio.newsly.common.PushId
import com.chipcerio.newsly.data.AppDatabase
import com.chipcerio.newsly.data.dto.Article
import com.chipcerio.newsly.data.dto.Source
import com.chipcerio.newsly.data.entity.ArticlesEntity
import com.chipcerio.newsly.data.entity.SourceEntity
import com.chipcerio.newsly.data.source.ArticleSource
import io.reactivex.Observable
import javax.inject.Inject

class ArticlesLocalSource @Inject
constructor(private val db: AppDatabase) : ArticleSource {

    override fun getArticles(sources: List<String>, page: Int): Observable<List<Article>> {

        val joinedSources = sources.joinToString { "," }
        /*
         * SELECT * FROM articles
         * LIMIT 5 OFFSET 20
         *
         * page 1 = offset  0  = (1 * 5) - 5
         * page 2 = offset  5  = (2 * 5) - 5
         * page 3 = offset 10  = (3 * 5) - 5
         * page 4 = offset 15  = (4 * 5) - 5
         * page 5 = offset 20  = (5 * 5) - 5
         */
        val offset = (page * LIMIT) - LIMIT

        return db.articlesDao().getArticlesByPage(offset).toObservable()
            .flatMapIterable { it }
            .map {
                val sourceModel = db.sourcesDao().getSource(it.source).blockingGet()
                val source = Source(
                    sourceModel.id,
                    sourceModel.name,
                    "",
                    "",
                    "",
                    "",
                    ""
                )
                Article(
                    id = System.currentTimeMillis(),
                    source = source,
                    author = it.author,
                    title = it.title,
                    description = it.description,
                    url = it.url,
                    urlToImage = it.urlToImage,
                    publishedAt = it.publishedAt)
            }.toList().toObservable()
    }

    override fun save(article: Article) {
        val articleEntity = ArticlesEntity(
            id = PushId.generate(),
            source = article.source.name,
            author = article.author,
            title = article.title,
            description = article.description,
            url = article.url,
            urlToImage = article.urlToImage,
            publishedAt = article.publishedAt
        )
        db.articlesDao().save(articleEntity)
    }

    override fun save(source: Source) {
        val sourceModel = SourceEntity(source.id, source.name)
        db.sourcesDao().save(sourceModel)
    }
}