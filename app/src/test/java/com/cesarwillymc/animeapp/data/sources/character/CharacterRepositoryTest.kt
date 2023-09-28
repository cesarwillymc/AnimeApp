package com.cesarwillymc.animeapp.data.sources.character

import com.cesarwillymc.animeapp.data.sources.character.remote.CharacterClient
import com.cesarwillymc.animeapp.util.constants.ZERO
import com.cesarwillymc.animeapp.util.state.Result
import com.cesarwillymc.animeapp.util.state.getData
import com.cesarwillymc.animeapp.util.state.isError
import com.cesarwillymc.animeapp.util.state.isSuccess
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import kotlin.Exception

class CharacterRepositoryTest {

    private lateinit var dataSource: CharacterDataSource

    @RelaxedMockK
    lateinit var client: CharacterClient

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        dataSource = CharacterRepository(client)
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
}