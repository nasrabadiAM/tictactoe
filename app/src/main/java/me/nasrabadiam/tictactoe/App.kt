package me.nasrabadiam.tictactoe

import android.app.Activity
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.compose.viewModel
import me.nasrabadiam.tictactoe.game.ui.GameScreen
import me.nasrabadiam.tictactoe.game.ui.GameViewModel
import me.tatarka.inject.annotations.Inject

typealias App = @Composable () -> Unit

@Inject
@Composable
fun App(gameViewModel: (SavedStateHandle) -> GameViewModel) {
    val windowSizeClass = getWindowSizeClass()
    val viewModel = viewModel { gameViewModel(createSavedStateHandle()) }
    GameScreen(viewModel, windowSizeClass)
}

@Composable
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
private fun getWindowSizeClass(): GameWindowSizeClass {
    val activity = LocalContext.current as Activity

    val windowSizeClass = calculateWindowSizeClass(activity)

    val gameWindowSizeClass = when {
        windowSizeClass.isSquareScreen() -> {
            GameWindowSizeClass.COMPACT
        }

        windowSizeClass.isVerticalScreen() -> {
            GameWindowSizeClass.NORMAL
        }

        else -> {
            GameWindowSizeClass.EXPANDED
        }
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

enum class GameWindowSizeClass {
    NORMAL, EXPANDED, COMPACT
}