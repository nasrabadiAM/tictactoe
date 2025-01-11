package me.nasrabadiam.tictactoe.ui

import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.layout.layout

internal fun Modifier.squareWrapContentLayout(): Modifier = composed {
    layout { measurable, constraints ->
        val minDimension = minOf(constraints.maxWidth, constraints.maxHeight)
        val placeable = measurable.measure(
            constraints.copy(
                maxWidth = minDimension,
                maxHeight = minDimension,
                minHeight = minDimension,
                minWidth = minDimension
            )
        )
        layout(minDimension, minDimension) {
            placeable.place(x = 0, y = 0)
        }
    }
}