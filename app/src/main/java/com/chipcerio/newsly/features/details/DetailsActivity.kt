package com.chipcerio.newsly.features.details

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.chipcerio.newsly.R
import com.chipcerio.newsly.config.NewslyGlide
import com.chipcerio.newsly.data.Article
import kotlinx.android.synthetic.main.activity_details.upNavigationView
import kotlinx.android.synthetic.main.activity_details.titleView
import kotlinx.android.synthetic.main.activity_details.descriptionView
import kotlinx.android.synthetic.main.activity_details.authorView
import kotlinx.android.synthetic.main.activity_details.dateView
import kotlinx.android.synthetic.main.activity_details.thumbnailView
import kotlinx.android.synthetic.main.activity_details.shareView

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
        authorView.text = article.author
        dateView.text = article.publishedAt
        NewslyGlide.with(this)
                .load(article.urlToImage)
                .into(thumbnailView)

        shareView.setOnClickListener {  }
    }
}
