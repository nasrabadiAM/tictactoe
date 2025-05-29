package me.nasrabadiam.tictactoe

import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import me.nasrabadiam.tictactoe.game.GameUseCase
import me.nasrabadiam.tictactoe.game.model.Game
import me.nasrabadiam.tictactoe.game.model.GameMode
import me.nasrabadiam.tictactoe.game.ui.GameScreen
import me.nasrabadiam.tictactoe.game.ui.GameViewModel
import me.nasrabadiam.tictactoe.home.HomeEvent
import me.nasrabadiam.tictactoe.home.HomeEvent.PlayWithAFriend
import me.nasrabadiam.tictactoe.home.HomeEvent.PlayWithAI
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
    val gameViewModel: (SavedStateHandle, Game) -> GameViewModel = { savedStateHandle, game ->
        GameViewModel(
            gameUseCase = gameUseCase.invoke(),
            savedStateHandle = savedStateHandle,
            gameArgs = game
        )
    }
    val windowSizeClass = getWindowSizeClass()

    val navController = rememberNavController()
    NavHost(
        modifier = Modifier.statusBarsPadding(),
        navController = navController,
        startDestination = HOME_SCREEN_ROUTE,
    ) {
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
            homeEvent = navigateToGame(navController)
        )
    }
}

private fun navigateToGame(navController: NavHostController): (HomeEvent) -> Unit = {
    when (it) {
        PlayWithAFriend -> navController.navigateToPlayWithAFriend()
        PlayWithAI -> navController.navigateToPlayWithAI()
    }
}

private fun NavGraphBuilder.gameScreen(
    gameViewModel: (SavedStateHandle, Game) -> GameViewModel,
    windowSizeClass: GameWindowSizeClass
) {
    composable<Game> { backStackEntry ->
        val game: Game = backStackEntry.toRoute()
        val viewModel = viewModel { gameViewModel(backStackEntry.savedStateHandle, game) }
        GameScreen(viewModel, windowSizeClass)
    }
}

private fun NavController.navigateToPlayWithAFriend() {
    navigate(Game(GameMode.PLAYER_VS_PLAYER))
}

private fun NavController.navigateToPlayWithAI() {
    navigate(Game(GameMode.PLAYER_VS_AI))
}

private const val HOME_SCREEN_ROUTE = "home"