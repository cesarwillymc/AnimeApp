package com.cesarwillymc.animeapp.data.sources.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.cesarwillymc.animeapp.data.sources.character.local.CharacterDao
import com.cesarwillymc.animeapp.data.sources.character.local.entities.CharacterDetailEntity

@Database(entities = [CharacterDetailEntity::class], version = 1, exportSchema = false)
@TypeConverters(TypeConvert::class)
abstract class AnimeRoomDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
}