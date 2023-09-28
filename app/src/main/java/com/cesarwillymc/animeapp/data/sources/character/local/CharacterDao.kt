package com.cesarwillymc.animeapp.data.sources.character.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cesarwillymc.animeapp.data.sources.character.local.entities.CharacterDetailEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(character: CharacterDetailEntity)
    @Query("DELETE FROM character WHERE id = :characterId")
    suspend fun delete(characterId: String)
    @Query("SELECT * FROM character")
    fun getAllCharacters(): Flow<List<CharacterDetailEntity>>
    @Query("SELECT * FROM character WHERE id = :characterId")
    suspend fun getCharacterById(characterId: String): CharacterDetailEntity
}