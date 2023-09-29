package com.cesarwillymc.animeapp.presentation.detail.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.res.dimensionResource
import com.cesarwillymc.animeapp.R
import com.cesarwillymc.animeapp.domain.usecase.entities.CharacterDetail

/**
 * Created by Willy on 11/17/2021.
 * cesarwilly.mc@gmail.com
 *
 * Lima, Peru.
 */

@Composable
fun DetailViewContent(
    character: CharacterDetail
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Black)
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
                style = MaterialTheme.typography.titleMedium,
                color = Gray
            )
            Text(
                text = character.species,
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.Normal100)))

            HorizontalDivider(modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.ThreeDp)))
//            TabCard(
//                title = stringResource(R.string.tv_detail_information_product),
//                drawable = painterResource(R.drawable.ic_information),
//                onClick = {
//                    onClickInfoProduct(TypeTabsAction.INFORMATION_PRODUCT)
//                }
//            )
        }
    }


}

