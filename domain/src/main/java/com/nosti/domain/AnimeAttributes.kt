package com.nosti.domain

data class AnimeAttributes(
    var attributeId: Int,
    var createdAt: String?,
    var synopsis: String?,
    var canonicalTitle: String?,
    var averageRating: String?,
    var startDate: String?,
    var ageRatingGuide: String?,
    var endDate: String?,
    var coverImage: AnimeCoverImage?,
    var posterImage: AnimePosterImage?
)