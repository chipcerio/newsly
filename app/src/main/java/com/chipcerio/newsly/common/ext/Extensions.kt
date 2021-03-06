package com.chipcerio.newsly.common.ext

import android.app.Activity
import android.support.v4.content.ContextCompat
import android.widget.ImageView
import android.widget.Toast
import com.chipcerio.newsly.R
import com.chipcerio.newsly.config.NewslyGlide
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter

fun Activity.toast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

fun String.toDisplayableDateTime(): String {
    // https://stackoverflow.com/a/22463063/1076574
    val isoInstantFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssX")
    val localDateTime = LocalDateTime.parse(this, isoInstantFormat)
    val fmt = DateTimeFormatter.ofPattern("dd MMM yyyy | hh:mma")
    return localDateTime.format(fmt)
}

fun ImageView.loadFromUrl(imageUrl: String) {
    NewslyGlide.with(this)
        .load(imageUrl)
        .fallback(ContextCompat.getDrawable(this.context, R.drawable.ic_launcher_background))
        .into(this)
}