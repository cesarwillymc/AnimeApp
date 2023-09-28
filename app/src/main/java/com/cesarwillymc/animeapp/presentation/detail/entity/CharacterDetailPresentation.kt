package com.cesarwillymc.animeapp.presentation.detail.entity

data class CharacterDetailPresentation(
    val created: String,
    val gender: String,
    val id: String,
    val image: String,
    val name: String,
    val species: String,
    val status: String,
    val type: String,
    val location: LocationPresentation?,
    val episode: List<EpisodePresentation>?,
    val origin: OriginPresentation?,
    val isWishlist: Boolean = false
)