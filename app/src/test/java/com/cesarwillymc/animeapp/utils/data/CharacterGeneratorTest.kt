package com.cesarwillymc.animeapp.utils.data

import com.cesarwillymc.animeapp.data.sources.character.entities.CharacterDetailResponse
import com.cesarwillymc.animeapp.data.sources.character.entities.CharacterItemResponse
import com.cesarwillymc.animeapp.data.sources.character.entities.CharacterListResponse
import com.cesarwillymc.animeapp.data.sources.character.entities.EpisodeResponse
import com.cesarwillymc.animeapp.data.sources.character.entities.LocationResponse
import com.cesarwillymc.animeapp.data.sources.character.entities.OriginResponse
import com.cesarwillymc.animeapp.data.sources.character.local.entities.CharacterDetailEntity

object CharacterGeneratorTest {
    const val characterID = "381"
    private val characterItem = listOf(
        CharacterItemResponse(
            image = "https://rickandmortyapi.com/api/character/avatar/381.jpeg",
            name = "Woman Rick",
            id = "381",
            species = "Alien",
            gender = "Female"
        ),
        CharacterItemResponse(
            image = "https://rickandmortyapi.com/api/character/avatar/382.jpeg",
            name = "Worldender",
            id = "382",
            species = "Alien",
            gender = "Male"
        ),
        CharacterItemResponse(
            image = "https://rickandmortyapi.com/api/character/avatar/383.jpeg",
            name = "Yaarb",
            id = "383",
            species = "Alien",
            gender = "Male"
        ),
        CharacterItemResponse(
            image = "https://rickandmortyapi.com/api/character/avatar/384.jpeg",
            name = "Yellow Headed Doctor",
            id = "384",
            species = "Alien",
            gender = "Male"
        ),
        CharacterItemResponse(
            image = "https://rickandmortyapi.com/api/character/avatar/385.jpeg",
            name = "Yellow Shirt Rick",
            id = "385",
            species = "Human",
            gender = "Male"
        ),
        CharacterItemResponse(
            image = "https://rickandmortyapi.com/api/character/avatar/386.jpeg",
            name = "Zarbadar Gloonch",
            id = "386",
            species = "Alien",
            gender = "Female"
        ),
        CharacterItemResponse(
            image = "https://rickandmortyapi.com/api/character/avatar/387.jpeg",
            name = "Zarbadar's Mytholog",
            id = "387",
            species = "Mythological Creature",
            gender = "Female"
        ),
        CharacterItemResponse(
            image = "https://rickandmortyapi.com/api/character/avatar/388.jpeg",
            name = "Zeep Xanflorp",
            id = "388",
            species = "Humanoid",
            gender = "Male"
        ),
        CharacterItemResponse(
            image = "https://rickandmortyapi.com/api/character/avatar/389.jpeg",
            name = "Zeta Alpha Rick",
            id = "389",
            species = "Human",
            gender = "Male"
        ),
        CharacterItemResponse(
            image = "https://rickandmortyapi.com/api/character/avatar/390.jpeg",
            name = "Zick Zack",
            id = "390",
            species = "Alien",
            gender = "Male"
        ),
        CharacterItemResponse(
            image = "https://rickandmortyapi.com/api/character/avatar/391.jpeg",
            name = "Uncle Steve",
            id = "391",
            species = "Alien",
            gender = "Male"
        ),
        CharacterItemResponse(
            image = "https://rickandmortyapi.com/api/character/avatar/392.jpeg",
            name = "Bearded Morty",
            id = "392",
            species = "Human",
            gender = "Male"
        ),
        CharacterItemResponse(
            image = "https://rickandmortyapi.com/api/character/avatar/393.jpeg",
            name = "Roy",
            id = "393",
            species = "Human",
            gender = "Male"
        ),
        CharacterItemResponse(
            image = "https://rickandmortyapi.com/api/character/avatar/394.jpeg",
            name = "Davin",
            id = "394",
            species = "Cronenberg",
            gender = "Male"
        ),
        CharacterItemResponse(
            image = "https://rickandmortyapi.com/api/character/avatar/395.jpeg",
            name = "Greebybobe",
            id = "395",
            species = "Alien",
            gender = "unknown"
        ),
        CharacterItemResponse(
            image = "https://rickandmortyapi.com/api/character/avatar/396.jpeg",
            name = "Scary Teacher",
            id = "396",
            species = "Mythological Creature",
            gender = "Male"
        ),
        CharacterItemResponse(
            image = "https://rickandmortyapi.com/api/character/avatar/397.jpeg",
            name = "Fido",
            id = "397",
            species = "Animal",
            gender = "Male"
        ),
        CharacterItemResponse(
            image = "https://rickandmortyapi.com/api/character/avatar/398.jpeg",
            name = "Accountant dog",
            id = "398",
            species = "Animal",
            gender = "Male"
        ),
        CharacterItemResponse(
            image = "https://rickandmortyapi.com/api/character/avatar/399.jpeg",
            name = "Tiny-persons advocacy group lawyer",
            id = "399",
            species = "Mythological Creature",
            gender = "Male"
        ),
        CharacterItemResponse(
            image = "https://rickandmortyapi.com/api/character/avatar/400.jpeg",
            name = "Giant Judge",
            id = "400",
            species = "Mythological Creature",
            gender = "Male"
        )
    )

    val characterList = CharacterListResponse(
        next = 21,
        items = characterItem
    )

    private val locationResponse = LocationResponse(
        dimension = "",
        name = "unknown",
        type = ""
    )

    private val episodeResponse = EpisodeResponse(
        airDate = "April 7, 2014",
        created = "2017-11-10T12:56:34.747Z",
        episode = "S01E10",
        name = "Close Rick-counters of the Rick Kind"
    )

    private val originResponse = OriginResponse(
        dimension = "",
        name = "unknown",
        type = ""
    )

    val characterDetailResponse = CharacterDetailResponse(
        created = "2018-01-10T19:46:00.622Z",
        gender = "Female",
        id = "381",
        image = "https://rickandmortyapi.com/api/character/avatar/381.jpeg",
        name = "Woman Rick",
        species = "Alien",
        status = "Alive",
        type = "Chair",
        location = locationResponse,
        episode = listOf(episodeResponse),
        origin = originResponse
    )

    val charactersListDB = listOf(
        CharacterDetailEntity(
            created = "2018-01-10T19:46:00.622Z",
            gender = "Female",
            id = "381",
            image = "https://rickandmortyapi.com/api/character/avatar/381.jpeg",
            name = "Woman Rick",
            species = "Alien",
            status = "Alive",
            type = "Chair",
            location = locationResponse,
            episode = listOf(episodeResponse),
            origin = originResponse
        )
    )
}
