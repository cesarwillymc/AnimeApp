package com.cesarwillymc.animeapp.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import com.cesarwillymc.animeapp.R

@Composable
fun CustomPrimaryButton(
    modifier: Modifier = Modifier,
    title: String,
    textColor: Color,
    isEnabled: Boolean = true,
    backGroundColor: Color = MaterialTheme.colorScheme.primary,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth(),
        enabled = isEnabled,
        shape = MaterialTheme.shapes.medium,
        colors = ButtonDefaults.buttonColors(
            containerColor = backGroundColor,
            contentColor = textColor,
            disabledContainerColor = Color.Gray,
            disabledContentColor = Color.White
        ),
        elevation =ButtonDefaults.buttonElevation()
    ) {
        Text(
            text = title,
            modifier = Modifier.padding(dimensionResource(id = R.dimen.Small100))
        )
    }
}
