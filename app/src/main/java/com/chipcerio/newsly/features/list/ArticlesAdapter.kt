package com.chipcerio.newsly.features.list

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.chipcerio.newsly.R
import com.chipcerio.newsly.config.NewslyGlide
import com.chipcerio.newsly.data.Article

class ArticlesAdapter(private val articles: MutableList<Article>,
                      private val onArticleClickListener: OnArticleClickListener,
                      private val onLoadItemsListener: OnLoadMoreItemsListener) : RecyclerView.Adapter<ArticlesAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(articles[position])
        if (position == articles.lastIndex) {
            onLoadItemsListener.onLoadMoreItems()
        }
    }

    override fun getItemCount(): Int = articles.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_article, parent, false))

    fun add(position: Int, article: Article) {
        articles.add(article)
        notifyItemChanged(position)
    }

    interface OnArticleClickListener {
        fun onArticleClick(article: Article)
    }

    interface OnLoadMoreItemsListener {
        fun onLoadMoreItems()
    }

    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        private var thumbnailView = view.findViewById<ImageView>(R.id.thumbnailView)
        private var titleView = view.findViewById<TextView>(R.id.titleView)
        private var descriptionView = view.findViewById<TextView>(R.id.descriptionView)
        private var fullStoryView = view.findViewById<TextView>(R.id.fullStoryView)

        fun bind(article: Article) {
            titleView.text = article.title
            descriptionView.text = article.description
            fullStoryView.setOnClickListener {
                onArticleClickListener.onArticleClick(article)
            }

            NewslyGlide.with(view.context)
                    .load(article.urlToImage)
                    .fallback(ContextCompat.getDrawable(view.context, R.drawable.ic_launcher_background))
                    .into(thumbnailView)
        }
    }
}