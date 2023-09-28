package com.cesarwillymc.animeapp.data.di

import com.apollographql.apollo3.ApolloClient
import com.cesarwillymc.animeapp.BuildConfig
import com.cesarwillymc.animeapp.data.sources.character.CharacterDataSource
import com.cesarwillymc.animeapp.data.sources.character.CharacterRepository
import com.cesarwillymc.animeapp.data.sources.character.remote.ApolloCharacterClient
import com.cesarwillymc.animeapp.data.sources.character.remote.CharacterClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    @Singleton
    fun provideApolloClient(): ApolloClient {
        return ApolloClient.Builder()
            .serverUrl(BuildConfig.url_graphql)
            .build()
    }

    @Provides
    @Singleton
    fun provideCharacterClient(apolloClient: ApolloClient): CharacterClient {
        return ApolloCharacterClient(apolloClient)
    }

    @Provides
    @Singleton
    fun provideCharacterDataSource(client: CharacterClient): CharacterDataSource {
        return CharacterRepository(client)
    }
}
