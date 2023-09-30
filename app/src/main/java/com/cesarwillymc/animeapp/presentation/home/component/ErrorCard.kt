package com.cesarwillymc.animeapp.presentation.home.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.cesarwillymc.animeapp.R

@Composable
fun ErrorCard(modifier: Modifier = Modifier, error: String, onClickRetry: () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier) {
        Image(
            painter = painterResource(id = R.drawable.ic_alert),
            contentDescription = "Icon Alert"
        )
        Text(
            text = error,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(bottom = dimensionResource(id = R.dimen.Normal100))
                .padding(horizontal = dimensionResource(id = R.dimen.Normal150))
                .fillMaxWidth(),
            style = MaterialTheme.typography.titleLarge
        )

        TextButton(
            onClick = onClickRetry,
            border = BorderStroke(
                dimensionResource(id = R.dimen.OneDp),
                MaterialTheme.colorScheme.surface
            )
        ) {
            Text(
                stringResource(id = R.string.til_try_again),
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

@Composable
@Preview(name = "Light Theme", showBackground = true)
fun ErrorCardPreview() {
    ErrorCard(
        error = stringResource(id = R.string.lbl_error),
        onClickRetry = {}
    )
}
