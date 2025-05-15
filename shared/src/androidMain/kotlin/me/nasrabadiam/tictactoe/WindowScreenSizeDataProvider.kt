package me.nasrabadiam.tictactoe

import android.app.Activity
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
internal fun getGameWindowSizeClass(): GameWindowSizeClass {
    val activity = LocalContext.current as Activity

    val windowSizeClass = calculateWindowSizeClass(activity)

    val gameWindowSizeClass = when {
        windowSizeClass.isSquareScreen() -> GameWindowSizeClass.COMPACT
        windowSizeClass.isVerticalScreen() -> GameWindowSizeClass.NORMAL
        else -> GameWindowSizeClass.EXPANDED
    }
    return gameWindowSizeClass
}

private fun WindowSizeClass.isSquareScreen(): Boolean {
    val isCompact = heightSizeClass == WindowHeightSizeClass.Compact &&
        widthSizeClass == WindowWidthSizeClass.Compact
    val isMedium = heightSizeClass == WindowHeightSizeClass.Medium &&
        widthSizeClass == WindowWidthSizeClass.Medium
    val isExpanded = widthSizeClass == WindowWidthSizeClass.Expanded &&
        heightSizeClass == WindowHeightSizeClass.Expanded
    return isCompact || isMedium || isExpanded
}

private fun WindowSizeClass.isVerticalScreen(): Boolean {
    return (heightSizeClass == WindowHeightSizeClass.Expanded &&
        widthSizeClass == WindowWidthSizeClass.Medium ||
        widthSizeClass == WindowWidthSizeClass.Compact) ||
        (heightSizeClass == WindowHeightSizeClass.Medium &&
            widthSizeClass == WindowWidthSizeClass.Compact)
}