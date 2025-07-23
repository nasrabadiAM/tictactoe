package me.nasrabadiam.tictactoe

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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import androidx.savedstate.SavedState
import androidx.savedstate.read
import androidx.savedstate.write
import me.nasrabadiam.tictactoe.game.GameUseCase
import me.nasrabadiam.tictactoe.game.model.AIDifficulty
import me.nasrabadiam.tictactoe.game.model.DifficultySelector
import me.nasrabadiam.tictactoe.game.model.Game
import me.nasrabadiam.tictactoe.game.model.GameMode
import me.nasrabadiam.tictactoe.game.model.GameMode.PlayWithFriend
import me.nasrabadiam.tictactoe.game.ui.GameScreen
import me.nasrabadiam.tictactoe.game.ui.GameViewModel
import me.nasrabadiam.tictactoe.home.DifficultyDialogScreen
import me.nasrabadiam.tictactoe.home.HomeEvent
import me.nasrabadiam.tictactoe.home.HomeEvent.PlayWithAFriendEvent
import me.nasrabadiam.tictactoe.home.HomeEvent.PlayWithAIEvent
import me.nasrabadiam.tictactoe.home.HomeScreen
import me.nasrabadiam.tictactoe.ui.theme.TacTrixTheme
import me.tatarka.inject.annotations.Inject
import kotlin.reflect.typeOf

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
        difficultyDialog(navController)
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
        PlayWithAFriendEvent -> navController.navigateToPlayWithAFriend()
        PlayWithAIEvent -> navController.navigateToPlayWithAIDifficultySelector()
    }
}

val gameModeNavType = object : NavType<GameMode>(isNullableAllowed = false) {
    override fun get(bundle: SavedState, key: String): GameMode {
        return bundle.read { getString(key).let { parseValue(it) } }
    }

    override fun parseValue(value: String): GameMode {
        return GameMode.decode(value) ?: PlayWithFriend
    }

    override fun put(bundle: SavedState, key: String, value: GameMode) {
        bundle.write { putString(key, serializeAsValue(value)) }
    }

    override fun serializeAsValue(value: GameMode): String {
        return value.encode()
    }
}

private fun NavGraphBuilder.gameScreen(
    gameViewModel: (SavedStateHandle, Game) -> GameViewModel,
    windowSizeClass: GameWindowSizeClass
) {
    composable<Game>(typeMap = mapOf(typeOf<GameMode>() to gameModeNavType)) { backStackEntry ->
        val game: Game = backStackEntry.toRoute()
        val viewModel = viewModel { gameViewModel(backStackEntry.savedStateHandle, game) }
        GameScreen(viewModel, windowSizeClass)
    }
}

private fun NavGraphBuilder.difficultyDialog(
    navController: NavHostController,
) {
    dialog<DifficultySelector> {
        val onDifficultySelected: (AIDifficulty) -> Unit = remember {
            { aiDifficulty -> navController.navigateToPlayWithAi(aiDifficulty) }
        }
        DifficultyDialogScreen(navController, onDifficultySelected)
    }
}

private fun NavController.navigateToPlayWithAFriend() {
    navigate(Game(PlayWithFriend))
}

private fun NavController.navigateToPlayWithAIDifficultySelector() {
    navigate(DifficultySelector)
}

private fun NavController.navigateToPlayWithAi(aiDifficulty: AIDifficulty) {
    navigate(Game(GameMode.PlayWithAI(aiDifficulty)))
}

private const val HOME_SCREEN_ROUTE = "home"