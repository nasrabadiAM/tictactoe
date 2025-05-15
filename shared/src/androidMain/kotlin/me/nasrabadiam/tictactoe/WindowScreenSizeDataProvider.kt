package me.nasrabadiam.tictactoe

import android.app.Activity
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
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