package com.chipcerio.newsly.data.source.local

import com.chipcerio.newsly.data.ArticleModel
import com.chipcerio.newsly.data.SourceModel
import com.chipcerio.newsly.data.raw_types.Article
import com.chipcerio.newsly.data.raw_types.Source
import com.chipcerio.newsly.data.source.ArticleSource
import io.reactivex.Observable
import java.util.concurrent.atomic.AtomicInteger
import javax.inject.Inject

class ArticlesLocalSource @Inject
constructor(private val db: AppDatabase) : ArticleSource {

    // https://stackoverflow.com/a/2179012/1076574
    // https://groups.google.com/forum/#!msg/realm-java/6hFqdyoH67w/232lFPuc0eYJ
    private val id = AtomicInteger()

    override fun getArticles(sources: List<String>, page: Int): Observable<List<Article>> {
        // "bbc-news,bloomberg", 4
        return db.articlesDao().getArticlesByPage(sources, page).toObservable()
            .flatMapIterable { it }
            .map {
                val sourceModel = db.sourcesDao().getSource(it.sourceId).blockingGet()
                val source = Source(sourceModel.id, sourceModel.name)
                Article(
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
        val articleModel = ArticleModel(
            id = id.getAndIncrement(),
            sourceId = article.source.id,
            author = article.author,
            title = article.title,
            description = article.description,
            url = article.url,
            urlToImage = article.urlToImage,
            publishedAt = article.publishedAt)
        db.articlesDao().save(articleModel)
    }

    override fun save(source: Source) {
        val sourceModel = SourceModel(source.id, source.name)
        db.sourcesDao().save(sourceModel)
    }
}