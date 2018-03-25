package com.chipcerio.newsly.api

import com.chipcerio.newsly.data.dto.Article
import com.chipcerio.newsly.data.dto.Source
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.io.File

class SourceDeserializerTest {

    private lateinit var gson: Gson

    @Before
    fun setup() {
        gson = GsonBuilder().run {
            registerTypeAdapter(Source::class.java, SourceDeserializer())
            registerTypeAdapter(Article::class.java, ArticleDeserializer())
            create()
        }
    }

    @Test
    fun test_SourceDeserializer_Deserializes_Successfully() {
        val path = javaClass.getResource("/EverythingResponse.json").path
        val reader = File(path).bufferedReader()

        val response = gson.fromJson(reader, ArticlesResponse::class.java)

        assertEquals("ok", response.status)
        assertEquals(80052, response.totalResults)

        response.articles.forEach {
            println("json: ${it.source}")
        }
    }
}