package me.nasrabadiam.tictactoe.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import me.nasrabadiam.tictactoe.ui.theme.utils.colorPack

val ColorScheme.transparent: Color
    @Composable
    get() = colorPack(TransparentColor, TransparentColor)