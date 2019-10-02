package com.nosti.domain

data class AnimeData(
    val id: Int,
    val type: String?,
    val attributes: AnimeAttributes?,
    val favorite: Boolean
)
