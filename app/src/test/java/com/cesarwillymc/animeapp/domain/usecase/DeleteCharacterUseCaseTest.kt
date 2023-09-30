package com.cesarwillymc.animeapp.domain.usecase

import com.cesarwillymc.animeapp.data.sources.character.CharacterDataSource
import com.cesarwillymc.animeapp.util.state.Result
import com.cesarwillymc.animeapp.util.state.isError
import com.cesarwillymc.animeapp.util.state.isSuccess
import com.cesarwillymc.animeapp.utils.MockkTest
import com.cesarwillymc.animeapp.utils.data.CharacterGeneratorTest.characterID
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class DeleteCharacterUseCaseTest : MockkTest() {
    @RelaxedMockK
    private lateinit var dataSource: CharacterDataSource

    private lateinit var useCase: DeleteCharacterUseCase

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        useCase = DeleteCharacterUseCase(dataSource, UnconfinedTestDispatcher())
    }

    @Test
    fun executeSuccess() = runTest {
        coEvery { dataSource.deleteCharacterDB(characterID) } returns Result.Success(
            Unit
        )

        val response = useCase(characterID)

        assertTrue(response.isSuccess)
    }

    @Test
    fun executeError() = runTest {
        coEvery { dataSource.deleteCharacterDB(characterID) } returns Result.Error(
            Exception()
        )

        val response = useCase(characterID)

        assertTrue(response.isError)
    }
}
