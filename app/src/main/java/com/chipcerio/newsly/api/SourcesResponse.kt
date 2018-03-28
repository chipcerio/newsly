package com.chipcerio.newsly.api

import com.chipcerio.newsly.data.dto.Source

class SourcesResponse(
    val status: String,

    val sources: List<Source>
)