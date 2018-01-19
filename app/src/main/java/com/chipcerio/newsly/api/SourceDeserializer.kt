package com.chipcerio.newsly.api

import com.chipcerio.newsly.data.raw_types.Source
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class SourceDeserializer : JsonDeserializer<Source> {

    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Source {
        val root = json.asJsonObject

        val id = if (root.get("id").isJsonNull)
            root.get("id").asString.replace("\\s".toRegex(), "").decapitalize()
        else root.get("id").asString

        val name = root.get("name").asString

        return Source(id, name)
    }
}