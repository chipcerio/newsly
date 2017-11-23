package com.chipcerio.newsly.api.response

import com.chipcerio.newsly.data.Article


class TopHeadlinesResponse (
        val status: String,
        val articles: MutableList<Article>
)

//class Article (
//        val source: Source,
//        val author: String,
//        val title: String,
//        val description: String,
//        val url: String,
//        val urlToImage: String,
//        val publishedAt: String
//) {
//    override fun toString(): String =
//            "author: $author\ntitle: $title\ndesc: $description\nurl: $url\nurlToImage: $urlToImage\npublishedAt: $publishedAt"
//}

class Source (
        val id: String,
        val name: String
)