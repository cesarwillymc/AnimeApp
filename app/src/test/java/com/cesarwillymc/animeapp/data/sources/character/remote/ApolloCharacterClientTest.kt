package com.cesarwillymc.animeapp.data.sources.character.remote

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.mockserver.MockResponse
import com.apollographql.apollo3.mockserver.MockServer
import com.cesarwillymc.animeapp.util.constants.EMPTY_STRING
import com.cesarwillymc.animeapp.util.constants.ONE
import com.cesarwillymc.animeapp.util.constants.ZERO
import com.cesarwillymc.animeapp.util.state.getData
import com.cesarwillymc.animeapp.util.state.getError
import com.cesarwillymc.animeapp.util.state.isError
import com.cesarwillymc.animeapp.util.state.isSuccess
import com.cesarwillymc.animeapp.utils.data.ApolloGeneratorTest
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class ApolloCharacterClientTest {

    @Test
    fun getCharactersError500() = runTest {
        // Create a mock server
        val mockServer = MockServer()
        val apolloClient = ApolloClient.Builder().serverUrl(mockServer.url()).build()
        val client = ApolloCharacterClient(apolloClient)

        // Enqueue HTTP responses
        mockServer.enqueue(
            MockResponse.Builder()
                .statusCode(500)
                .body("Internal server error")
                .delayMillis(1000L).build()
        )

        client.getCharacters(ZERO).let {
            assertTrue(it.isError)
            assertTrue(it.getError().message?.contains("500") ?: false)
        }

        mockServer.stop()
    }

    @Test
    fun getCharactersError404() = runTest {
        // Create a mock server
        val mockServer = MockServer()
        val apolloClient = ApolloClient.Builder().serverUrl(mockServer.url()).build()
        val client = ApolloCharacterClient(apolloClient)

        // Enqueue HTTP responses
        mockServer.enqueue(
            MockResponse.Builder()
                .statusCode(404)
                .body("No found")
                .delayMillis(1000L).build()
        )

        client.getCharacters(ZERO).let {
            assertTrue(it.isError)
            assertTrue(it.getError().message?.contains("404") ?: false)
        }

        mockServer.stop()
    }

    @Test
    fun getCharactersSuccess() = runTest {
        // Create a mock server
        val mockServer = MockServer()
        val apolloClient = ApolloClient.Builder().serverUrl(mockServer.url()).build()
        val client = ApolloCharacterClient(apolloClient)

        // Enqueue HTTP responses
        mockServer.enqueue(
            MockResponse.Builder()
                .statusCode(200)
                .body(ApolloGeneratorTest.characters)
                .delayMillis(1000L).build()
        )

        client.getCharacters(ONE).let {
            assertTrue(it.isSuccess)
            assertTrue(it.getData().items.isNotEmpty())
            assertEquals(20, it.getData().items.size)
        }

        mockServer.stop()
    }

    @Test
    fun getDetailSuccess() = runTest {
        // Create a mock server
        val mockServer = MockServer()
        val apolloClient = ApolloClient.Builder().serverUrl(mockServer.url()).build()
        val client = ApolloCharacterClient(apolloClient)

        // Enqueue HTTP responses
        mockServer.enqueue(
            MockResponse.Builder()
                .statusCode(200)
                .body(ApolloGeneratorTest.character381)
                .delayMillis(1000L).build()
        )

        client.getDetail(ApolloGeneratorTest.characterID).let {
            assertTrue(it.isSuccess)
            assertEquals(ApolloGeneratorTest.characterID, it.getData()?.id)
            assertEquals("Female", it.getData()?.gender)
            assertEquals("Woman Rick", it.getData()?.name)
            assertEquals("Alien", it.getData()?.species)
            assertEquals("Alive", it.getData()?.status)
        }

        mockServer.stop()
    }

    @Test
    fun getDetailErrorNoFound() = runTest {
        // Create a mock server
        val mockServer = MockServer()
        val apolloClient = ApolloClient.Builder().serverUrl(mockServer.url()).build()
        val client = ApolloCharacterClient(apolloClient)

        // Enqueue HTTP responses
        mockServer.enqueue(
            MockResponse.Builder()
                .statusCode(200)
                .body(ApolloGeneratorTest.characterNoFound)
                .delayMillis(1000L).build()
        )

        client.getDetail(EMPTY_STRING).let {
            assertTrue(it.isSuccess)
            assertNull(it.getData())
        }

        mockServer.stop()
    }

    @Test
    fun getDetailError500() = runTest {
        // Create a mock server
        val mockServer = MockServer()
        val apolloClient = ApolloClient.Builder().serverUrl(mockServer.url()).build()
        val client = ApolloCharacterClient(apolloClient)

        // Enqueue HTTP responses
        mockServer.enqueue(
            MockResponse.Builder()
                .statusCode(500)
                .body("Internal server error")
                .delayMillis(1000L).build()
        )

        client.getDetail(EMPTY_STRING).let {
            assertTrue(it.isError)
        }

        mockServer.stop()
    }
}
