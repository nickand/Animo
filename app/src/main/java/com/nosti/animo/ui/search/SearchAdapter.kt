package com.nosti.animo.ui.search

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.nosti.animo.R
import com.nosti.animo.ui.common.basicDiffUtil
import com.nosti.animo.ui.common.inflate
import com.nosti.animo.ui.common.loadUrl
import com.nosti.animo.ui.common.setRoundCorners
import com.nosti.domain.AnimeData
import kotlinx.android.synthetic.main.item_scalable.view.*

class SearchAdapter(private val listener: (AnimeData) -> Unit) :
    RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    var animeListCopy: ArrayList<AnimeData> = ArrayList()

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

    fun clear() {
        animeListCopy.clear()
        animes.toMutableList().clear()
        notifyDataSetChanged()
    }

    fun isEmpty(): Boolean {
        return itemCount == 0
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        lateinit var animeImage: AppCompatImageView
        lateinit var animeTitle: TextView

        fun bind(anime: AnimeData) {
            animeImage = itemView.findViewById(R.id.thumbnail)
            animeTitle = itemView.findViewById(R.id.titleAnime)
            animeTitle.text = anime.attributes?.canonicalTitle
            animeImage.setRoundCorners(R.dimen.spacing_xxxs)
            anime.attributes?.posterImage?.medium?.let { itemView.thumbnail.loadUrl(it) }
        }
    }
}