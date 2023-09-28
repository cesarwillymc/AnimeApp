package com.cesarwillymc.animeapp.domain.usecase

import com.cesarwillymc.animeapp.data.sources.character.CharacterDataSource
import com.cesarwillymc.animeapp.data.sources.character.CharacterGeneratorTest
import com.cesarwillymc.animeapp.util.constants.ZERO
import com.cesarwillymc.animeapp.util.state.Result
import com.cesarwillymc.animeapp.util.state.getData
import com.cesarwillymc.animeapp.util.state.isError
import com.cesarwillymc.animeapp.util.state.isSuccess
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test
import java.lang.Exception

class GetCharactersUseCaseTest {
    @RelaxedMockK
    private lateinit var dataSource: CharacterDataSource

    private lateinit var useCase: GetCharactersUseCase

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        useCase = GetCharactersUseCase(dataSource, UnconfinedTestDispatcher())
    }

    @Test
    fun executeSuccess() = runTest {
        coEvery { dataSource.getCharacters(ZERO) } returns Result.Success(
            UseCaseCharacterGeneratorTest.characterList
        )

        val response = useCase(ZERO)

        assertTrue(response.isSuccess)
        response.getData().let {
            assertEquals(it.items.size, 20)
            assertEquals(it.next, 21)
        }
    }

    @Test
    fun executeError() = runTest {
        coEvery { dataSource.getCharacters(ZERO) } returns Result.Error(
            Exception()
        )

        val response = useCase(ZERO)

        assertTrue(response.isError)
    }
}