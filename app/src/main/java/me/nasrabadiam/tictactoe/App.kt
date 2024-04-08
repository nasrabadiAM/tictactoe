package me.nasrabadiam.tictactoe

import android.app.Activity
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
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

    val widthSizeClass = windowSizeClass.widthSizeClass
    val heightSizeClass = windowSizeClass.heightSizeClass

    return when {
        heightSizeClass == WindowHeightSizeClass.Compact &&
            widthSizeClass == WindowWidthSizeClass.Compact -> {
            GameWindowSizeClass.COMPACT
        }

        widthSizeClass != WindowWidthSizeClass.Compact -> {
            GameWindowSizeClass.EXPANDED
        }

        else -> {
            GameWindowSizeClass.NORMAL
        }
    }
}

enum class GameWindowSizeClass {
    NORMAL, EXPANDED, COMPACT
}