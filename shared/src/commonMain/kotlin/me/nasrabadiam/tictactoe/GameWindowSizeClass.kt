package me.nasrabadiam.tictactoe

import androidx.compose.runtime.Composable

enum class GameWindowSizeClass {
    NORMAL, EXPANDED, COMPACT
}

@Composable
expect fun getWindowSizeClass(): GameWindowSizeClass