package com.chipcerio.newsly.api.response

import com.chipcerio.newsly.data.Article

class TopHeadlinesResponse(
    val status: String,
    val articles: List<Article>
)

class Source(
    val id: String,
    val name: String
)