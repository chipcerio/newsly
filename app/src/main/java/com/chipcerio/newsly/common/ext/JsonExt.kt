package com.chipcerio.newsly.common.ext

import com.google.gson.JsonObject

fun JsonObject.getOrElse(key: String): String {
    return if (this.get(key).isJsonNull) "" else this.get(key).asString
}