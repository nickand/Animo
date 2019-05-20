package com.nosti.animo.activities

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nosti.animo.R

class DetailActivity : AppCompatActivity() {

    companion object {
        const val ANIME = "DetailActivity:anime"
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_detail_app)

        /*with(intent.getParcelableExtra<Movie>(MOVIE)) {
            movieDetailToolbar.title = title
            movieDetailImage.loadUrl("https://image.tmdb.org/t/p/w780$backdropPath")

            movieDetailSummary.text = overview

            movieDetailInfo.text = buildSpannedString {

                bold { append("Original language: ") }
                appendln(originalLanguage)

                bold { append("Original title: ") }
                appendln(originalTitle)

                bold { append("Release date: ") }
                appendln(releaseDate)

                bold { append("Popularity: ") }
                appendln(popularity.toString())

                bold { append("Vote Average: ") }
                append(voteAverage.toString())
            }
        }*/
    }
}
