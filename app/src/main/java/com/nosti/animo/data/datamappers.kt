package com.nosti.animo.data

import com.nosti.domain.AnimeData
import com.nosti.animo.data.AnimeAttributes as RoomAttributes
import com.nosti.animo.data.AnimeCoverImage as RoomCoverImage
import com.nosti.animo.data.AnimePosterImage as RoomPosterImage
import com.nosti.animo.data.database.AnimeData as DomainAnime
import com.nosti.animo.data.server.AnimeData as ServerAnime
import com.nosti.domain.AnimeAttributes as DomainAttributes
import com.nosti.domain.AnimeCoverImage as DomainCoverImage
import com.nosti.domain.AnimePosterImage as DomainPosterImage


fun AnimeData.toRoomAnime(): DomainAnime {
    return DomainAnime(
        id,
        type ?: "anime",
        getDataAttributes(this.attributes),
        favorite
    )
}

fun DomainAnime.toDomainAnime(): AnimeData {
    return AnimeData(
        id,
        type.toString(),
        getDataAttributes(this.attributes),
        favorite
    )
}

fun ServerAnime.toDomainAnime(): AnimeData {
    return AnimeData(
        id,
        type.toString(),
        getDataAttributes(this.attributes),
        favorite
    )
}

fun getDataAttributes(attributes: RoomAttributes?): DomainAttributes? {
    attributes?.let {
        return DomainAttributes(
            it.attributeId,
            it.createdAt.toString(),
            it.synopsis.toString(),
            it.canonicalTitle.toString(),
            it.averageRating.toString(),
            it.startDate.toString(),
            it.ageRatingGuide.toString(),
            it.endDate ?: "Ongoing",
            getDataCoverImage(attributes.coverImage),
            getDataPosterImage(attributes.posterImage)
        )
    }
    return null
}

fun getDataAttributes(attributes: DomainAttributes?): RoomAttributes? {
    attributes?.let {
        return RoomAttributes(
            it.attributeId,
            it.createdAt.toString(),
            it.synopsis.toString(),
            it.canonicalTitle.toString(),
            it.averageRating.toString(),
            it.startDate.toString(),
            it.ageRatingGuide.toString(),
            it.endDate ?: "Ongoing",
            attributes.coverImage?.let { coverImage -> getDataCoverImage(coverImage) },
            attributes.posterImage?.let { posterImage -> getDataPosterImage(posterImage) }
        )
    }
    return null
}

fun getDataCoverImage(coverImage: RoomCoverImage?): DomainCoverImage? {
    coverImage?.let {
        return DomainCoverImage(it.large)
    }
    return null
}

fun getDataPosterImage(posterImage: RoomPosterImage?): DomainPosterImage? {
    posterImage?.let {
        return DomainPosterImage(it.medium)
    }
    return null
}

fun getDataCoverImage(coverImage: DomainCoverImage): RoomCoverImage {
    return RoomCoverImage(coverImage.large ?: "")
}

fun getDataPosterImage(posterImage: DomainPosterImage): RoomPosterImage {
    return RoomPosterImage(posterImage.medium ?: "")
}