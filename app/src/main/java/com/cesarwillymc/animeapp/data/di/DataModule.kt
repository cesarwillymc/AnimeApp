package com.cesarwillymc.animeapp.data.di

import android.content.Context
import androidx.room.Room
import com.apollographql.apollo3.ApolloClient
import com.cesarwillymc.animeapp.BuildConfig
import com.cesarwillymc.animeapp.data.sources.character.CharacterDataSource
import com.cesarwillymc.animeapp.data.sources.character.CharacterRepository
import com.cesarwillymc.animeapp.data.sources.character.local.CharacterDao
import com.cesarwillymc.animeapp.data.sources.character.remote.ApolloCharacterClient
import com.cesarwillymc.animeapp.data.sources.character.remote.CharacterClient
import com.cesarwillymc.animeapp.data.sources.db.AnimeRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun provideAppDatabase(@ApplicationContext appContext: Context): AnimeRoomDatabase {
        return Room.databaseBuilder(
            appContext,
            AnimeRoomDatabase::class.java,
            BuildConfig.name_database
        ).build()
    }

    @Provides
    fun provideCharacterDao(appDatabase: AnimeRoomDatabase): CharacterDao {
        return appDatabase.characterDao()
    }

    @Provides
    @Singleton
    fun provideCharacterClient(apolloClient: ApolloClient): CharacterClient {
        return ApolloCharacterClient(apolloClient)
    }

    @Provides
    @Singleton
    fun provideCharacterDataSource(
        client: CharacterClient,
        characterDao: CharacterDao
    ): CharacterDataSource {
        return CharacterRepository(client, characterDao)
    }
}
