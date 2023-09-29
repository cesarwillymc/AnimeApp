package com.cesarwillymc.animeapp.domain.usecase

import com.cesarwillymc.animeapp.data.sources.character.CharacterDataSource
import com.cesarwillymc.animeapp.util.constants.ZERO
import com.cesarwillymc.animeapp.util.state.Result
import com.cesarwillymc.animeapp.util.state.getData
import com.cesarwillymc.animeapp.util.state.isError
import com.cesarwillymc.animeapp.util.state.isSuccess
import com.cesarwillymc.animeapp.utils.MockkTest
import com.cesarwillymc.animeapp.utils.data.UseCaseCharacterGeneratorTest
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class GetCharactersUseCaseTest : MockkTest() {
    @RelaxedMockK
    private lateinit var dataSource: CharacterDataSource

    private lateinit var useCase: GetCharactersUseCase

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
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
