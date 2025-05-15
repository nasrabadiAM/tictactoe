package me.nasrabadiam.tictactoe

import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
actual fun getWindowSizeClass(): GameWindowSizeClass {
    /*val windowSizeClass = calculateWindowSizeClass()

    val gameWindowSizeClass = when {
        windowSizeClass.isSquareScreen() -> GameWindowSizeClass.COMPACT
        windowSizeClass.isVerticalScreen() -> GameWindowSizeClass.NORMAL
        else -> GameWindowSizeClass.EXPANDED
    }
    return gameWindowSizeClass*/
    return GameWindowSizeClass.EXPANDED
}