package com.nosti.animo.ui.main

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nosti.animo.R
import com.nosti.animo.model.AnimeData
import com.nosti.animo.ui.basicDiffUtil
import com.nosti.animo.ui.inflate
import com.nosti.animo.ui.loadUrl
import kotlinx.android.synthetic.main.item_scalable.view.*

class AnimoAdapter(private val listener: (AnimeData) -> Unit) :
    RecyclerView.Adapter<AnimoAdapter.ViewHolder>() {

    var animes: List<AnimeData> by basicDiffUtil(
        emptyList(),
        areItemsTheSame = { old, new -> old.id == new.id }
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.item_scalable, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = animes.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val anime = animes[position]
        holder.bind(anime)
        holder.itemView.setOnClickListener {
            listener(anime)
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(anime: AnimeData) {
            anime.attributes?.posterImage?.medium?.let { itemView.thumbnail.loadUrl(it) }
        }
    }
}