package com.nosti.animo.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.nosti.animo.R
import com.nosti.animo.extensions.basicDiffUtil
import com.nosti.animo.extensions.inflate
import com.nosti.animo.extensions.loadUrl
import com.nosti.animo.models.AnimeData
import kotlinx.android.synthetic.main.item_scalable.view.*
import kotlin.properties.Delegates

class AnimoAdapter(private val listener: (AnimeData) -> Unit) :
    RecyclerView.Adapter<AnimoAdapter.ViewHolder>() {

    var animes: List<AnimeData> by basicDiffUtil(
        emptyList(),
        areItemsTheSame = { old, new -> old.id == new.id }
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimoAdapter.ViewHolder {
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
            anime.attributes.posterImage?.medium?.let { itemView.thumbnail.loadUrl(it) }
        }
    }
}