package com.chipcerio.newsly.features.list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.chipcerio.newsly.R
import com.chipcerio.newsly.data.Article

class ArticlesAdapter(private val articles: MutableList<Article>) : RecyclerView.Adapter<ArticlesAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(articles[position])
    }

    override fun getItemCount(): Int = articles.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_article, parent, false))

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private var titleView = view.findViewById<TextView>(R.id.titleView)
        private var authorView = view.findViewById<TextView>(R.id.authorView)

        fun bind(article: Article) {
            titleView.text = article.title
            authorView.text = article.author
        }
    }
}