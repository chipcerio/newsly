package com.chipcerio.newsly.api

import com.chipcerio.newsly.common.ext.getOrElse
import com.chipcerio.newsly.data.raw_types.Article
import com.chipcerio.newsly.data.raw_types.Source
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class ArticleDeserializer : JsonDeserializer<Article> {

    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Article {
        val root = json.asJsonObject

        val sourceJson = root.getAsJsonObject("source")
        val id = sourceJson.getOrElse("id")
        val name = sourceJson.getOrElse("name")
        val source = Source(id, name)

        val author = root.getOrElse("author")
        val title = root.getOrElse("title")
        val description = root.getOrElse("description")
        val url = root.getOrElse("url")
        val urlToImage = root.getOrElse("urlToImage")
        val publishedAt = root.getOrElse("publishedAt")

        return Article(
            source = source,
            author = author,
            title = title,
            description = description,
            url = url,
            urlToImage = urlToImage,
            publishedAt = publishedAt)
    }
}