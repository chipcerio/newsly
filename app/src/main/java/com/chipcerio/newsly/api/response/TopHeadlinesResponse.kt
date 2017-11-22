package com.chipcerio.newsly.api.response


class TopHeadlinesResponse (
        val status: String,
        val articles: MutableList<Article>
)

class Article (
        val source: Source,
        val author: String,
        val title: String,
        val description: String,
        val url: String,
        val urlToImage: String,
        val publishedAt: String
)

class Source (
        val id: String,
        val name: String
)