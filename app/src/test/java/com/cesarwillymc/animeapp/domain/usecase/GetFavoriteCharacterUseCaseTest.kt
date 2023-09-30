package com.cesarwillymc.animeapp.domain.usecase

import app.cash.turbine.test
import com.cesarwillymc.animeapp.data.sources.character.CharacterDataSource
import com.cesarwillymc.animeapp.utils.MockkTest
import com.cesarwillymc.animeapp.utils.data.UseCaseCharacterGeneratorTest.characterList
import com.cesarwillymc.animeapp.utils.data.UseCaseCharacterGeneratorTest.newItem
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetFavoriteCharacterUseCaseTest : MockkTest() {
    @RelaxedMockK
    private lateinit var dataSource: CharacterDataSource

    private lateinit var useCase: GetFavoriteCharactersUseCase

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        useCase = GetFavoriteCharactersUseCase(dataSource, UnconfinedTestDispatcher())
    }

    @Test
    fun execute() = runTest {
        val characterList = characterList.items

        val flowOfCharacterList = MutableStateFlow(characterList)

        coEvery { dataSource.getCharactersFromDB() } returns flowOfCharacterList

        useCase(Unit).test {
            val initialSize = characterList.size
            assertEquals(initialSize, awaitItem().size)
            val updatedCharacterList = characterList + newItem
            flowOfCharacterList.value = updatedCharacterList
            assertEquals(updatedCharacterList.size, awaitItem().size)
            cancelAndIgnoreRemainingEvents()
        }
    }
}
