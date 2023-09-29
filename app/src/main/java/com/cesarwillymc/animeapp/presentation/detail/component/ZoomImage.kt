package com.cesarwillymc.animeapp.presentation.detail.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import coil.compose.rememberImagePainter
import com.cesarwillymc.animeapp.util.constants.ONE_F
import com.cesarwillymc.animeapp.util.constants.ZERO
import com.cesarwillymc.animeapp.util.constants.ZERO_F
import kotlin.math.abs

@Composable
fun ZoomImage(
    imageUrl: String,
    height: Dp
) {
    var scale by remember { mutableFloatStateOf(ONE_F) }
    var offset by remember { mutableStateOf(Offset.Zero) }
    val state = rememberTransformableState { zoomChange, offsetChange, _ ->
        if (scale >= ONE_F) {
            scale *= zoomChange
        } else {
            scale = ONE_F
        }
        offset += offsetChange
    }
    var direction by remember { mutableFloatStateOf(-ONE_F) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RectangleShape)
            .background(MaterialTheme.colorScheme.background)
            .pointerInput(Unit) {
                detectDragGestures(
                    onDrag = { change, dragAmount ->
                        change.consume()
                        val (x, y) = dragAmount
                        if (abs(x) > abs(y)) {
                            when {
                                x > ZERO -> {
                                    direction = ZERO_F
                                }

                                x < ZERO -> {
                                    direction = ONE_F
                                }
                            }
                        }
                    }
                )
            },
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberImagePainter(imageUrl, builder = {
                crossfade(true)
            }),
            contentDescription = null,
            modifier = Modifier
                .height(height)
                .fillMaxWidth()
                .align(Alignment.CenterVertically)
                .graphicsLayer {
                    scaleX = scale
                    scaleY = scale
                    translationX = offset.x
                    translationY = offset.y
                }
                .transformable(state = state)
        )
    }
}
