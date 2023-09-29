package com.cesarwillymc.animeapp.presentation.home.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.cesarwillymc.animeapp.R

@Composable
fun LoadingView(modifier: Modifier = Modifier) {
    Box(modifier) {
        CircularProgressIndicator(
            modifier = Modifier
                .padding(vertical = dimensionResource(id = R.dimen.Normal100))
                .align(Alignment.Center)
                .size(dimensionResource(id = R.dimen.Large100))
        )
    }
}

@Composable
@Preview(name = "Light Theme", showBackground = true)
fun LoadingViewPreview() {
    LoadingView(modifier = Modifier.size(dimensionResource(id = R.dimen.ImageMedium)))
}
