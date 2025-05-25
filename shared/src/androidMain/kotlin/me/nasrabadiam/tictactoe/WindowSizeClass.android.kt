package me.nasrabadiam.tictactoe

import androidx.compose.runtime.Composable

@Composable
actual fun getWindowSizeClass(): GameWindowSizeClass {
    return getGameWindowSizeClass()
}
