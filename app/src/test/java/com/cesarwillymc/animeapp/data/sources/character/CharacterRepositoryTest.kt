package com.cesarwillymc.animeapp.data.sources.character

import com.cesarwillymc.animeapp.data.sources.character.local.CharacterDao
import com.cesarwillymc.animeapp.data.sources.character.remote.CharacterClient
import com.cesarwillymc.animeapp.util.constants.ZERO
import com.cesarwillymc.animeapp.util.state.Result
import com.cesarwillymc.animeapp.util.state.getData
import com.cesarwillymc.animeapp.util.state.isError
import com.cesarwillymc.animeapp.util.state.isSuccess
import com.cesarwillymc.animeapp.utils.MockkTest
import com.cesarwillymc.animeapp.utils.data.CharacterGeneratorTest
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class CharacterRepositoryTest : MockkTest() {

    private lateinit var dataSource: CharacterDataSource

    @RelaxedMockK
    lateinit var client: CharacterClient

    @RelaxedMockK
    lateinit var characterDao: CharacterDao

    @Before
    fun setUp() {
        dataSource = CharacterRepository(client, characterDao)
    }

    @Test
    fun getCharactersSuccess() = runTest {
        coEvery {
            client.getCharacters(ZERO)
        } returns Result.Success(CharacterGeneratorTest.characterList)

        val response = dataSource.getCharacters(ZERO)

        assertTrue(response.isSuccess)
        response.getData().let {
            assertEquals(it.items.size, 20)
            assertEquals(it.next, 21)
        }
    }

    @Test
    fun getCharactersError() = runTest {
        coEvery {
            client.getCharacters(ZERO)
        } returns Result.Error(Exception())

        val response = dataSource.getCharacters(ZERO)

        assertTrue(response.isError)
    }

    @Test
    fun getDetailSuccess() = runTest {
        coEvery {
            client.getDetail(CharacterGeneratorTest.characterID)
        } returns Result.Success(CharacterGeneratorTest.characterDetailResponse)

        val response = dataSource.getDetail(CharacterGeneratorTest.characterID)
        assertTrue(response.isSuccess)
        response.getData().let {
            assertEquals(it?.id, CharacterGeneratorTest.characterID)
            assertEquals(it?.name, CharacterGeneratorTest.characterDetailResponse.name)
            assertEquals(
                it?.location?.name,
                CharacterGeneratorTest.characterDetailResponse.location?.name
            )
        }
    }

    @Test
    fun getDetailError() = runTest {
        coEvery {
            client.getDetail(CharacterGeneratorTest.characterID)
        } returns Result.Error(Exception())

        val response = dataSource.getDetail(CharacterGeneratorTest.characterID)
        assertTrue(response.isError)
    }
    /*

    @Test
    fun getCharactersFromDBSuccess() = runTest {
        val charactersFromDb = CharacterGeneratorTest.charactersListDB
        coEvery { characterDao.getAllCharacters() } returns flowOf(charactersFromDb)

        val response = dataSource.getCharactersFromDB().first()

        assertEquals(charactersFromDb.first(), response)
    }

    @Test
    fun getDetailFromDBSuccess() = runTest {
        val characterId = CharacterGeneratorTest.charactersListDB
        val characterDetailFromDb = CharacterGeneratorTest.characterDetail

        coEvery { characterDao.getCharacterById(characterId) } returns characterDetailFromDb

        val response = dataSource.getDetailFromDB(characterId)

        assertTrue(response.isSuccess)
        assertEquals(characterDetailFromDb, response.getData())
    }

    @Test
    fun addCharacterDBSuccess() = runTest {
        val characterDetailToAdd = CharacterGeneratorTest.charactersListDB.first()

        coEvery { characterDao.insert(characterDetailToAdd) } returns Unit

        val response = dataSource.addCharacterDB(characterDetailToAdd)

        assertTrue(response.isSuccess)
    }

    @Test
    fun deleteCharacterDBSuccess() = runTest {
        val characterIdToDelete = CharacterGeneratorTest.characterID

        coEvery { characterDao.delete(characterIdToDelete) } returns Unit

        val response = dataSource.deleteCharacterDB(characterIdToDelete)

        assertTrue(response.isSuccess)
    }
     */
}
