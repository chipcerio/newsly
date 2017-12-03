package com.chipcerio.newsly.features.list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.chipcerio.newsly.R
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

        private var titleView = view.findViewById<TextView>(R.id.titleView)
        private var descriptionView = view.findViewById<TextView>(R.id.descriptionView)

        fun bind(article: Article) {
            titleView.text = article.title
            descriptionView.text = article.description
            view.setOnClickListener {
                onArticleClickListener.onArticleClick(article)
            }
        }
    }
}