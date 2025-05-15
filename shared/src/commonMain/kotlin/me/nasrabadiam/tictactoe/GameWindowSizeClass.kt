package me.nasrabadiam.tictactoe

import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable

enum class GameWindowSizeClass {
    NORMAL, EXPANDED, COMPACT
}

@Composable
expect fun getWindowSizeClass(): GameWindowSizeClass

fun WindowSizeClass.isSquareScreen(): Boolean {
    val isCompact = heightSizeClass == WindowHeightSizeClass.Compact &&
        widthSizeClass == WindowWidthSizeClass.Compact
    val isMedium = heightSizeClass == WindowHeightSizeClass.Medium &&
        widthSizeClass == WindowWidthSizeClass.Medium
    val isExpanded = widthSizeClass == WindowWidthSizeClass.Expanded &&
        heightSizeClass == WindowHeightSizeClass.Expanded
    return isCompact || isMedium || isExpanded
}

fun WindowSizeClass.isVerticalScreen(): Boolean {
    return (heightSizeClass == WindowHeightSizeClass.Expanded &&
        widthSizeClass == WindowWidthSizeClass.Medium ||
        widthSizeClass == WindowWidthSizeClass.Compact) ||
        (heightSizeClass == WindowHeightSizeClass.Medium &&
            widthSizeClass == WindowWidthSizeClass.Compact)
}