package me.nasrabadiam.tictactoe

import androidx.compose.runtime.Composable
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import me.nasrabadiam.tictactoe.game.GameUseCase
import me.nasrabadiam.tictactoe.game.ui.GameScreen
import me.nasrabadiam.tictactoe.game.ui.GameViewModel
import me.nasrabadiam.tictactoe.home.HomeScreen
import me.nasrabadiam.tictactoe.ui.theme.TacTrixTheme
import me.tatarka.inject.annotations.Inject

typealias App = @Composable () -> Unit

@Inject
@Composable
fun App(gameUseCase: () -> GameUseCase) {
    TacTrixTheme {
        TacTrixApp(gameUseCase)
    }
}

@Composable
internal fun TacTrixApp(gameUseCase: () -> GameUseCase) {
    val gameViewModel: (SavedStateHandle) -> GameViewModel = {
        GameViewModel(gameUseCase.invoke(), savedStateHandle = it)
    }
    val windowSizeClass = getWindowSizeClass()

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = HOME_SCREEN_ROUTE) {
        homeScreen(windowSizeClass, navController)
        gameScreen(gameViewModel, windowSizeClass)
    }
}

private fun NavGraphBuilder.homeScreen(
    windowSizeClass: GameWindowSizeClass,
    navController: NavHostController
) {
    composable(HOME_SCREEN_ROUTE) {
        HomeScreen(
            windowSizeClass = windowSizeClass,
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