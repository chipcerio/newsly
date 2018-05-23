package com.chipcerio.newsly.features.sources_list

import android.graphics.Color
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.chipcerio.newsly.R
import com.chipcerio.newsly.data.dto.Source

class SourcesAdapter(
    private val sources: MutableList<Source>
) : RecyclerView.Adapter<SourcesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
            .inflate(android.R.layout.simple_list_item_1, parent, false))
    }

    override fun getItemCount(): Int = sources.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(sources[position])
    }

    fun add(position: Int, source: Source) {
        sources.add(source)
        notifyItemChanged(position)
    }

    inner class ViewHolder(private val containerView: View) : RecyclerView.ViewHolder(containerView) {

        private var src: TextView = containerView.findViewById(android.R.id.text1)

        fun bind(source: Source) {
            src.text = source.name

            val accent = ResourcesCompat.getColor(containerView.resources, R.color.colorAccent, null)

            // https://stackoverflow.com/a/45318967/1076574
            val color = if (source.selected) accent else Color.WHITE
            containerView.setBackgroundColor(color)

            containerView.setOnClickListener {
                source.selected = !source.selected
                it.setBackgroundColor(if (source.selected) accent else Color.WHITE)
            }
        }
    }
}