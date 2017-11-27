package com.chipcerio.newsly.api.response

import com.chipcerio.newsly.data.Article


class TopHeadlinesResponse (
        val status: String,
        val articles: MutableList<Article>
)

class Source (
        val id: String,
        val name: String
)