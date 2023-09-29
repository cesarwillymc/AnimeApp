package com.cesarwillymc.animeapp.domain.usecase

import com.cesarwillymc.animeapp.data.sources.character.CharacterDataSource
import com.cesarwillymc.animeapp.di.IoDispatcher
import com.cesarwillymc.animeapp.domain.base.SuspendUseCase
import com.cesarwillymc.animeapp.domain.usecase.entities.CharacterDetail
import com.cesarwillymc.animeapp.util.state.Result
import com.cesarwillymc.animeapp.util.state.dataOrNull
import com.cesarwillymc.animeapp.util.state.isSuccess
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetCharacterDetailUseCase @Inject constructor(
    private val repository: CharacterDataSource,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : SuspendUseCase<String, CharacterDetail?>(
    dispatcher
) {
    override suspend fun execute(parameters: String): Result<CharacterDetail?> {
        val dbResponse = repository.getDetailFromDB(parameters)
        return if (dbResponse.isSuccess && dbResponse.dataOrNull() != null) {
            dbResponse
        } else {
            repository.getDetail(parameters)
        }
    }
}
