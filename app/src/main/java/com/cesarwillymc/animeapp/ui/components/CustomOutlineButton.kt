package com.cesarwillymc.animeapp.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.cesarwillymc.animeapp.R

@SuppressWarnings("LongParameterList")
@Composable
fun CustomOutlineButton(
    modifier: Modifier = Modifier,
    title: String,
    onBackgroundColor: Color = MaterialTheme.colorScheme.background,
    elevation: ButtonElevation = ButtonDefaults.buttonElevation(),
    isEnabled: Boolean = true,
    borderColor: Color = MaterialTheme.colorScheme.primary,
    onClick: () -> Unit
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth(),
        enabled = isEnabled,
        shape = MaterialTheme.shapes.medium,
        border = BorderStroke(dimensionResource(id = R.dimen.OneDp), borderColor),
        colors = ButtonDefaults.buttonColors(
            contentColor = borderColor,
            containerColor = onBackgroundColor
        ),
        elevation = elevation
    ) {
        Text(
            text = title,
            modifier = Modifier.padding(dimensionResource(id = R.dimen.Small100)),
            style = MaterialTheme.typography.titleMedium
        )
    }
}

@Composable
@Preview(name = "Light Theme", showBackground = true)
fun CustomOutlineButtonPreview() {
    CustomOutlineButton(
        title = "Submit",
        onBackgroundColor = MaterialTheme.colorScheme.background,
        elevation = ButtonDefaults.buttonElevation(),
        isEnabled = true,
        borderColor = MaterialTheme.colorScheme.primary,
        onClick = {}
    )
}
