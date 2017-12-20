package com.chipcerio.newsly.common.ext

import android.app.Activity
import android.content.Context
import android.support.v4.content.ContextCompat
import android.widget.ImageView
import android.widget.Toast
import com.chipcerio.newsly.R
import com.chipcerio.newsly.config.NewslyGlide

fun Activity.toast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

fun ImageView.setImage(context: Context, url: String) {
    NewslyGlide.with(context)
            .load(url)
            .fallback(ContextCompat.getDrawable(context, R.drawable.ic_launcher_background))
            .into(this)
}