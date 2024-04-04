package me.nasrabadiam.tictactoe

import android.app.Activity
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import me.nasrabadiam.tictactoe.game.GameUseCase
import me.tatarka.inject.annotations.Inject

typealias App = @Composable () -> Unit

@Inject
@Composable
fun App(gameUseCase: GameUseCase) {
    val windowSizeClass = getWindowSizeClass()

    MainScreen(gameUseCase, windowSizeClass)
}

@Composable
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
private fun getWindowSizeClass(): WindowClass {
    val activity = LocalContext.current as Activity

    val windowSizeClass = calculateWindowSizeClass(activity)

    val widthSizeClass = windowSizeClass.widthSizeClass
    val heightSizeClass = windowSizeClass.heightSizeClass

    return when {
        heightSizeClass == WindowHeightSizeClass.Compact && widthSizeClass == WindowWidthSizeClass.Compact -> {
            WindowClass.COMPACT
        }

        widthSizeClass != WindowWidthSizeClass.Compact -> {
            WindowClass.EXPANDED
        }

        else -> {
            WindowClass.NORMAL
        }
    }
}

enum class WindowClass {
    NORMAL, EXPANDED, COMPACT
}