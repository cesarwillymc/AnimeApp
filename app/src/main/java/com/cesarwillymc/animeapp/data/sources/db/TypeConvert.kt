package com.cesarwillymc.animeapp.data.sources.db

import androidx.room.TypeConverter
import com.cesarwillymc.animeapp.data.sources.character.entities.EpisodeResponse
import com.cesarwillymc.animeapp.data.sources.character.entities.LocationResponse
import com.cesarwillymc.animeapp.data.sources.character.entities.OriginResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TypeConvert {
    private val gson = Gson()

    @TypeConverter
    fun fromEpisodeResponseList(episodes: List<EpisodeResponse>?): String? {
        return gson.toJson(episodes)
    }

    @TypeConverter
    fun toEpisodeResponseList(episodesString: String?): List<EpisodeResponse>? {
        if (episodesString == null) {
            return null
        }
        val listType = object : TypeToken<List<EpisodeResponse>>() {}.type
        return gson.fromJson(episodesString, listType)
    }

    @TypeConverter
    fun fromLocationResponse(location: LocationResponse?): String? {
        return gson.toJson(location)
    }

    @TypeConverter
    fun toLocationResponse(location: String?): LocationResponse? {
        if (location == null) {
            return null
        }
        val type = object : TypeToken<LocationResponse>() {}.type
        return gson.fromJson(location, type)
    }

    @TypeConverter
    fun fromOriginResponse(origin: OriginResponse?): String? {
        return gson.toJson(origin)
    }

    @TypeConverter
    fun toOriginResponse(origin: String?): OriginResponse? {
        if (origin == null) {
            return null
        }
        val type = object : TypeToken<OriginResponse>() {}.type
        return gson.fromJson(origin, type)
    }
}