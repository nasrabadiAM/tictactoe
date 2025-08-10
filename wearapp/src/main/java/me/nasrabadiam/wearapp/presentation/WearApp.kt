package me.nasrabadiam.wearapp.presentation

import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.composable
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import me.nasrabadiam.tictactoe.game.GameUseCase
import me.nasrabadiam.tictactoe.game.model.AIDifficulty
import me.nasrabadiam.tictactoe.game.model.Game
import me.nasrabadiam.tictactoe.game.model.GameMode
import me.nasrabadiam.tictactoe.game.model.GameMode.PlayWithFriend
import me.nasrabadiam.tictactoe.game.ui.GameViewModel
import me.nasrabadiam.tictactoe.home.DifficultyDialogScreen
import me.nasrabadiam.tictactoe.home.HomeEvent
import me.nasrabadiam.tictactoe.home.HomeEvent.PlayWithAFriendEvent
import me.nasrabadiam.tictactoe.home.HomeEvent.PlayWithAIEvent
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
    val gameViewModel: (SavedStateHandle, Game) -> GameViewModel = { savedStateHandle, game ->
        GameViewModel(
            gameUseCase = gameUseCase.invoke(),
            savedStateHandle = savedStateHandle,
            gameArgs = game
        )
    }
    val navController = rememberSwipeDismissableNavController()
    SwipeDismissableNavHost(
        modifier = Modifier.statusBarsPadding(),
        navController = navController,
        startDestination = HOME_SCREEN_ROUTE,
    ) {
        homeScreen(navController)
        gameScreen(gameViewModel)
        difficultyDialog(navController)
    }
}

private fun NavGraphBuilder.homeScreen(
    navController: NavHostController,
) {
    composable(HOME_SCREEN_ROUTE) {
        WearHomeScreen(
            homeEvent = navigateToGame(navController)
        )
    }
}

private fun NavGraphBuilder.gameScreen(
    gameViewModel: (SavedStateHandle, Game) -> GameViewModel,
) {
    composable(
        route = "$GAME_SCREEN_ROUTE/{$GAME_MODE_KEY}",
        arguments = listOf(
            navArgument(GAME_MODE_KEY, { type = NavType.StringType }),
        ),
    ) { backStackEntry ->
        val gameModeString = backStackEntry.arguments?.getString(GAME_MODE_KEY)
        val game = Game(GameMode.decode(gameModeString)!!)
        val viewModel = viewModel { gameViewModel(backStackEntry.savedStateHandle, game) }
        WearGameScreen(viewModel)
    }
}

private fun NavGraphBuilder.difficultyDialog(
    navController: NavHostController,
) {
    composable(
        route = DIFFICULTY_SELECTOR_SCREEN_ROUTE,
    ) {
        val onDifficultySelected: (AIDifficulty) -> Unit = remember {
            { aiDifficulty ->
                navController.navigateToPlayWithAi(aiDifficulty)
            }
        }
        DifficultyDialogScreen(navController, onDifficultySelected)
    }
}

private fun navigateToGame(navController: NavHostController): (HomeEvent) -> Unit = {
    when (it) {
        PlayWithAFriendEvent -> navController.navigateToPlayWithAFriend()
        PlayWithAIEvent -> navController.navigateToPlayWithAIDifficultySelector()
    }
}

private fun NavController.navigateToPlayWithAFriend() {
    navigate(route = "$GAME_SCREEN_ROUTE/${PlayWithFriend.encode()}")
}

private fun NavController.navigateToPlayWithAIDifficultySelector() {
    navigate(route = DIFFICULTY_SELECTOR_SCREEN_ROUTE)
}

private fun NavController.navigateToPlayWithAi(aiDifficulty: AIDifficulty) {
    navigate(route = "$GAME_SCREEN_ROUTE/${GameMode.PlayWithAI(aiDifficulty).encode()}") {
        popUpTo(route = HOME_SCREEN_ROUTE)
    }
}

private const val GAME_SCREEN_ROUTE = "game_screen"
private const val HOME_SCREEN_ROUTE = "home"
private const val DIFFICULTY_SELECTOR_SCREEN_ROUTE = "difficulty_selector"
private const val GAME_MODE_KEY = "gameMode"
