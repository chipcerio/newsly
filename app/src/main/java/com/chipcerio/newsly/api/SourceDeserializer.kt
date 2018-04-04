package com.chipcerio.newsly.api

import com.chipcerio.newsly.data.dto.Source
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class SourceDeserializer : JsonDeserializer<Source> {

    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Source {
        val root = json.asJsonObject

        val id = if (root.get("id").isJsonNull) {
            root.get("name").asString.decapitalize()
        } else {
            root.get("id").asString.decapitalize()
        }

        val name = root.get("name").asString
        val description = root.get("description").asString
        val url = root.get("url").asString
        val category = root.get("category").asString
        val language = root.get("language").asString
        val country = root.get("country").asString

        return Source(id, name, description, url, category, language, country)
    }
}