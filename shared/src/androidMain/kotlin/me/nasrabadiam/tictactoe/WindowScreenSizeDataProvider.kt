package me.nasrabadiam.tictactoe

import android.app.Activity
import androidx.activity.compose.LocalActivity
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable

@Composable
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
internal fun getGameWindowSizeClass(): GameWindowSizeClass {
    val activity = LocalActivity.current as Activity

    val windowSizeClass = calculateWindowSizeClass(activity)

    val gameWindowSizeClass = when {
        windowSizeClass.isSquareScreen() -> GameWindowSizeClass.COMPACT
        windowSizeClass.isVerticalScreen() -> GameWindowSizeClass.NORMAL
        else -> GameWindowSizeClass.EXPANDED
    }
    return gameWindowSizeClass
}