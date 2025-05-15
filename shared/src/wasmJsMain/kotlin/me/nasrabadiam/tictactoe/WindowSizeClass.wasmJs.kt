package me.nasrabadiam.tictactoe

import androidx.compose.runtime.Composable

@Composable
actual fun getWindowSizeClass(): GameWindowSizeClass {
    // TODO: return correct size class according to screen size
    return GameWindowSizeClass.EXPANDED
}