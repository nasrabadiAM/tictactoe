package me.nasrabadiam.wearapp.presentation

import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.composable
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import me.nasrabadiam.tictactoe.GameWindowSizeClass
import me.nasrabadiam.tictactoe.game.GameUseCase
import me.nasrabadiam.tictactoe.game.ui.GameScreen
import me.nasrabadiam.tictactoe.game.ui.GameViewModel
import me.nasrabadiam.tictactoe.getWindowSizeClass
import me.nasrabadiam.tictactoe.ui.theme.TacTrixTheme
import me.tatarka.inject.annotations.Inject

typealias WearApp = @Composable () -> Unit

@Inject
@Composable
fun WearApp(gameUseCase: () -> GameUseCase) {
    TacTrixTheme {
        TacTrixWearApp(gameUseCase)
    }
}

@Composable
internal fun TacTrixWearApp(gameUseCase: () -> GameUseCase) {
    val gameViewModel: (SavedStateHandle) -> GameViewModel = {
        GameViewModel(gameUseCase.invoke(), savedStateHandle = it)
    }
    val windowSizeClass = getWindowSizeClass()

    val navController = rememberSwipeDismissableNavController()
    SwipeDismissableNavHost(
        modifier = Modifier.statusBarsPadding(),
        navController = navController,
        startDestination = HOME_SCREEN_ROUTE,
    ) {
        homeScreen(navController)
        gameScreen(gameViewModel, windowSizeClass)
    }
}

private fun NavGraphBuilder.homeScreen(
    navController: NavHostController,
) {
    composable(HOME_SCREEN_ROUTE) {
        WearHomeScreen(
            homeEvent = { navController.navigateToGameScreen() }
        )
    }
}

private fun NavGraphBuilder.gameScreen(
    gameViewModel: (SavedStateHandle) -> GameViewModel,
    windowSizeClass: GameWindowSizeClass
) {
    composable(GAME_SCREEN_ROUTE) {
        val viewModel = viewModel { gameViewModel(createSavedStateHandle()) }
        GameScreen(viewModel, windowSizeClass)
    }
}

internal fun NavController.navigateToGameScreen() {
    navigate(GAME_SCREEN_ROUTE)
}

private const val GAME_SCREEN_ROUTE = "game_screen"
private const val HOME_SCREEN_ROUTE = "home"