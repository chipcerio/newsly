package com.chipcerio.newsly.api

import com.chipcerio.newsly.data.raw_types.Article

class ArticlesResponse (
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
)