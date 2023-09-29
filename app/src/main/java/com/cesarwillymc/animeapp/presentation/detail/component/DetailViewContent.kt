package com.cesarwillymc.animeapp.presentation.detail.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.cesarwillymc.animeapp.R
import com.cesarwillymc.animeapp.domain.usecase.entities.CharacterDetail
import com.cesarwillymc.animeapp.domain.usecase.entities.Location
import com.cesarwillymc.animeapp.domain.usecase.entities.Origin
import com.cesarwillymc.animeapp.ui.theme.AnimeAppTheme

@Composable
fun DetailViewContent(
    character: CharacterDetail
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        ZoomImage(
            imageUrl = character.image,
            height = dimensionResource(id = R.dimen.ImageLarge)
        )
        Column(
            modifier = Modifier
                .padding(horizontal = dimensionResource(id = R.dimen.Normal100))
        ) {
            Spacer(modifier = Modifier.padding(top = dimensionResource(id = R.dimen.ThreeDp)))
            Text(
                text = character.name,
                style = MaterialTheme.typography.headlineLarge,
                color = Gray
            )
            Text(
                text = character.species,
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.Normal100)))

            HorizontalDivider(modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.ThreeDp)))
            TabCard(
                title = character.origin?.name.orEmpty(),
                drawable = painterResource(R.drawable.ic_galaxy),
                subtitle = stringResource(R.string.til_origin),
                enabledSubContent = true,
                subContent = {
                    Text(
                        text = stringResource(
                            R.string.desc_dimension,
                            character.origin?.dimension.orEmpty()
                        ),
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Start
                    )

                    Text(
                        text = stringResource(R.string.desc_type, character.origin?.type.orEmpty()),
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Start
                    )
                }
            )
            HorizontalDivider(modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.ThreeDp)))
            TabCard(
                title = character.location?.name.orEmpty(),
                drawable = painterResource(R.drawable.ic_location),
                subtitle = stringResource(R.string.til_current_location),
                enabledSubContent = true,
                subContent = {
                    Text(
                        text = stringResource(
                            R.string.desc_dimension,
                            character.location?.dimension.orEmpty()
                        ),
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Start
                    )

                    Text(
                        text = stringResource(R.string.desc_type, character.location?.type.orEmpty()),
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Start
                    )
                }
            )
        }
    }
}

@Composable
@Preview(name = "Light Theme", showBackground = true)
fun CharacterDetailPreview() {
    AnimeAppTheme(darkTheme = false) {
        DetailViewContent(
            CharacterDetail(
                created = "2018-01-10T19:46:00.622Z",
                gender = "Female",
                id = "381",
                image = "https://rickandmortyapi.com/api/character/avatar/381.jpeg",
                name = "Woman Rick",
                species = "Alien",
                status = "Alive",
                type = "Chair",
                location = Location("E234", "Earth","Planet"),
                episode = listOf(),
                origin = Origin("Andromeda", "Earth 3242","Galaxy")
            )
        )
    }
}
