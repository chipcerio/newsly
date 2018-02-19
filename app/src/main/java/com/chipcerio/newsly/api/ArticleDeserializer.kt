package com.chipcerio.newsly.api

import com.chipcerio.newsly.common.ext.getOrElse
import com.chipcerio.newsly.data.dto.Article
import com.chipcerio.newsly.data.dto.Source
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class ArticleDeserializer : JsonDeserializer<Article> {

    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Article {
        val root = json.asJsonObject

        val sourceJson = root.getAsJsonObject("source")
        val id = sourceJson.getOrElse<String>("id")
        val name = sourceJson.getOrElse<String>("name")
        val source = Source(id, name)

        val articleId = root.getOrElse<Int>("id")
        val author = root.getOrElse<String>("author")
        val title = root.getOrElse<String>("title")
        val description = root.getOrElse<String>("description")
        val url = root.getOrElse<String>("url")
        val urlToImage = root.getOrElse<String>("urlToImage")
        val publishedAt = root.getOrElse<String>("publishedAt")

        return Article(
            id = articleId,
            source = source,
            author = author,
            title = title,
            description = description,
            url = url,
            urlToImage = urlToImage,
            publishedAt = publishedAt)
    }
}