package com.chipcerio.newsly.common.ext

import com.google.gson.JsonObject

inline fun <reified T> JsonObject.getOrElse(key: String): T {
    return when (T::class) {
        String::class -> {
            if (has(key)) {
                if (get(key).isJsonNull) "" as T else get(key).asString as T
            } else "" as T
        }

        Int::class -> {
            if (has(key)) {
                if (get(key).isJsonNull) 0 as T else get(key).asInt as T
            } else 0 as T
        }

        Long::class -> {
            if (has(key)) {
                if (get(key).isJsonNull) 0L as T else get(key).asLong as T
            } else 0L as T
        }

        Boolean::class -> {
            if (has(key)) {
                if (get(key).isJsonNull) false as T else get(key).asBoolean as T
            } else false as T
        }

        // Long
        // Char
        // Double

        else -> Any() as T
    }
}