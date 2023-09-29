package com.cesarwillymc.animeapp.data.sources.character.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.cesarwillymc.animeapp.data.sources.character.entities.EpisodeResponse
import com.cesarwillymc.animeapp.data.sources.character.entities.LocationResponse
import com.cesarwillymc.animeapp.data.sources.character.entities.OriginResponse
import com.cesarwillymc.animeapp.data.sources.db.TypeConvert

@Entity(tableName = "character")
data class CharacterDetailEntity(
    var created: String,
    var gender: String,
    @PrimaryKey var id: String,
    var image: String,
    var name: String,
    var species: String,
    var status: String,
    var type: String,
    @TypeConverters(TypeConvert::class)
    var location: LocationResponse? = null,
    @TypeConverters(TypeConvert::class)
    var episode: List<EpisodeResponse>? = listOf(),
    @TypeConverters(TypeConvert::class)
    var origin: OriginResponse? = null
)
