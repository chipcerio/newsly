package com.chipcerio.newsly.api

import com.chipcerio.newsly.data.Article

class ArticlesResponse (

    val status: String,

    val totalResults: Int,

    val articles: List<Article>
)