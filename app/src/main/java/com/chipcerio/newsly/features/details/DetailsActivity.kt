package com.chipcerio.newsly.features.details

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.chipcerio.newsly.R
import com.chipcerio.newsly.common.ext.loadFromUrl
import com.chipcerio.newsly.common.ext.toDisplayableDateTime
import com.chipcerio.newsly.data.Article
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    companion object {
        val EXTRA_ARTICLE = "extras:article"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val article = intent.getParcelableExtra<Article>(EXTRA_ARTICLE)

        upNavigationView.setOnClickListener { finish() }

        titleView.text = article.title
        descriptionView.text = article.description
        article.author?.let { authorView.text = it }
        dateView.text = article.publishedAt.toDisplayableDateTime()
        article.urlToImage?.let { thumbnailView.loadFromUrl(it) }

        shareView.setOnClickListener { }
    }
}
