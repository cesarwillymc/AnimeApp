package com.cesarwillymc.animeapp.domain.usecase.entities

data class CharacterDetail(
    val created: String,
    val gender: String,
    val id: String,
    val image: String,
    val name: String,
    val species: String,
    val status: String,
    val type: String,
    val location: Location?,
    val episode: List<Episode>?,
    val origin: Origin?,
    val isWishlist: Boolean = false
)
