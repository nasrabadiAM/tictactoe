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
import androidx.navigation.navDeepLink
import me.nasrabadiam.tictactoe.game.ui.GameScreen
import me.nasrabadiam.tictactoe.game.ui.GameViewModel
import me.nasrabadiam.tictactoe.home.HomeScreen
import me.nasrabadiam.tictactoe.ui.GameWindowSizeClass
import me.nasrabadiam.tictactoe.ui.getWindowSizeClass
import me.tatarka.inject.annotations.Inject

typealias App = @Composable () -> Unit

@Inject
@Composable
fun App(gameViewModel: (SavedStateHandle) -> GameViewModel) {
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
    composable(route = HOME_SCREEN_ROUTE) {
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
    composable(
        route = GAME_SCREEN_ROUTE,
        deepLinks = listOf(navDeepLink { uriPattern = "tactrix://start-game" })
    ) {
        val viewModel = viewModel { gameViewModel(createSavedStateHandle()) }
        GameScreen(viewModel, windowSizeClass)
    }
}

internal fun NavController.navigateToGameScreen() {
    navigate(GAME_SCREEN_ROUTE)
}

private const val GAME_SCREEN_ROUTE = "game_screen"
private const val HOME_SCREEN_ROUTE = "home"