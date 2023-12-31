package com.cesarwillymc.animeapp.domain.usecase

import com.cesarwillymc.animeapp.data.sources.character.CharacterDataSource
import com.cesarwillymc.animeapp.util.state.Result
import com.cesarwillymc.animeapp.util.state.getData
import com.cesarwillymc.animeapp.util.state.isError
import com.cesarwillymc.animeapp.util.state.isSuccess
import com.cesarwillymc.animeapp.utils.MockkTest
import com.cesarwillymc.animeapp.utils.data.CharacterGeneratorTest
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

class GetCharacterDetailUseCaseTest : MockkTest() {
    @RelaxedMockK
    private lateinit var dataSource: CharacterDataSource

    private lateinit var useCase: GetCharacterDetailUseCase

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        useCase = GetCharacterDetailUseCase(dataSource, UnconfinedTestDispatcher())
    }

    @Test
    fun executeSuccess() = runTest {
        coEvery { dataSource.getDetail(UseCaseCharacterGeneratorTest.characterID) } returns Result.Success(
            UseCaseCharacterGeneratorTest.characterDetail
        )
        val response = useCase(UseCaseCharacterGeneratorTest.characterID)

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
    fun executeError() = runTest {
        coEvery { dataSource.getDetail(UseCaseCharacterGeneratorTest.characterID) } returns Result.Error(
            Exception()
        )

        val response = useCase(UseCaseCharacterGeneratorTest.characterID)

        assertTrue(response.isError)
    }
}
