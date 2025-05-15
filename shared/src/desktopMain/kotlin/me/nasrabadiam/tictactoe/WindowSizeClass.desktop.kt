package me.nasrabadiam.tictactoe

import androidx.compose.runtime.Composable
import me.nasrabadiam.tictactoe.GameWindowSizeClass.EXPANDED

@Composable
actual fun getWindowSizeClass(): GameWindowSizeClass {
    // TODO: return correct size class according to screen size
    return EXPANDED
}