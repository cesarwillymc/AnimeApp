package com.cesarwillymc.animeapp.domain.usecase

import com.cesarwillymc.animeapp.data.sources.character.CharacterDataSource
import com.cesarwillymc.animeapp.util.state.Result
import com.cesarwillymc.animeapp.util.state.isError
import com.cesarwillymc.animeapp.util.state.isSuccess
import com.cesarwillymc.animeapp.utils.MockkTest
import com.cesarwillymc.animeapp.utils.data.UseCaseCharacterGeneratorTest.characterDetail
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class SaveCharacterUseCaseTest : MockkTest() {
    @RelaxedMockK
    private lateinit var dataSource: CharacterDataSource

    private lateinit var useCase: SaveCharacterUseCase

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        useCase = SaveCharacterUseCase(dataSource, UnconfinedTestDispatcher())
    }

    @Test
    fun executeSuccess() = runTest {
        coEvery { dataSource.addCharacterDB(characterDetail) } returns Result.Success(
            Unit
        )

        val response = useCase(characterDetail)

        assertTrue(response.isSuccess)
    }

    @Test
    fun executeError() = runTest {
        coEvery { dataSource.addCharacterDB(characterDetail) } returns Result.Error(
            Exception()
        )

        val response = useCase(characterDetail)

        assertTrue(response.isError)
    }
}
