package com.cesarwillymc.animeapp.presentation.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberImagePainter
import com.cesarwillymc.animeapp.R
import com.cesarwillymc.animeapp.domain.usecase.entities.CharacterItem
import com.cesarwillymc.animeapp.ui.theme.AnimeAppTheme

@Composable
fun CharacterCard(character: CharacterItem?, onclickCharacter: (String) -> Unit) {
    val context = LocalContext.current
    character?.let {
        Card(
            shape = RoundedCornerShape(dimensionResource(id = R.dimen.Normal100)),
            elevation = CardDefaults.cardElevation(dimensionResource(id = R.dimen.Small50)),
            modifier = Modifier
                .clickable {
                    onclickCharacter(character.id)
                }
                .padding(bottom = dimensionResource(id = R.dimen.Normal100))
                .fillMaxWidth()
                .semantics {
                    stateDescription =
                        context.getString(R.string.desc_card_character, character.name)
                }
        ) {
            Row(modifier = Modifier.padding(dimensionResource(id = R.dimen.Normal100))) {
                Box(
                    modifier = Modifier
                        .size(dimensionResource(id = R.dimen.ImageMedium))
                        .clip(shape = CircleShape)
                ) {
                    Image(
                        painter = rememberImagePainter(character.image),
                        contentDescription = stringResource(R.string.desc_card_character_image),
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
                Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.Small100)))
                Column(verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.Small50))) {
                    Text(text = character.name, style = MaterialTheme.typography.titleMedium)
                    HorizontalDivider(modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.Small50)))
                    Text(text = character.species, style = MaterialTheme.typography.titleSmall)
                    Text(text = character.gender, style = MaterialTheme.typography.titleSmall)
                }
            }
        }
    }
}

@Composable
@Preview(name = "Light Theme", showBackground = true)
fun CustomCharacterCardPreview() {
    AnimeAppTheme(darkTheme = false) { // Use your custom theme with darkTheme set to false
        CharacterCard(
            character = CharacterItem(
                image = "https://rickandmortyapi.com/api/character/avatar/21.jpeg",
                name = "Aqua Morty",
                id = "21",
                species = "Humanoid",
                gender = "Male"
            ),
            onclickCharacter = {
                //  Todo
            }
        )
    }
}
