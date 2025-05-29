package me.nasrabadiam.tictactoe

import androidx.lifecycle.SavedStateHandle
import dev.mokkery.MockMode.original
import dev.mokkery.matcher.any
import dev.mokkery.mock
import dev.mokkery.verify
import dev.mokkery.verify.VerifyMode.Companion.exactly
import dev.mokkery.verifySuspend
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import me.nasrabadiam.tictactoe.game.GameUseCase
import me.nasrabadiam.tictactoe.game.model.Game
import me.nasrabadiam.tictactoe.game.model.GameMode
import me.nasrabadiam.tictactoe.game.model.GameMode.PLAYER_VS_AI
import me.nasrabadiam.tictactoe.game.model.GameMode.PLAYER_VS_PLAYER
import me.nasrabadiam.tictactoe.game.ui.GameEvent
import me.nasrabadiam.tictactoe.game.ui.GameViewModel
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GameViewModelShould {

    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()
    private val gameUseCase = mock<GameUseCase>(original)
    private lateinit var gameViewModel: GameViewModel

    @BeforeTest
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        gameViewModel =
            GameViewModel(
                gameUseCase,
                SavedStateHandle(),
                Game(PLAYER_VS_PLAYER),
            )
    }

    @Test
    fun callReplayGameWhenReplayEventReceived() = runTest {

        gameViewModel.handleEvent(GameEvent.ReplayClicked)
        verifySuspend(exactly(1)) {
            gameUseCase.replayGame()
        }
    }

    @Test
    fun callRestartGameWhenRestartEventReceived() = runTest {

        gameViewModel.handleEvent(GameEvent.RestartClicked)
        verifySuspend(exactly(1)) {
            gameUseCase.restartGame()
        }
    }

    @Test
    fun callCellClickedWhenCellClickedEventReceived() = runTest {

        gameViewModel.handleEvent(GameEvent.CellClicked(1))
        verifySuspend(exactly(1)) {
            gameUseCase.clickOnCell(1)
        }
    }

    @Test
    fun callRestoreGameWhenViewModelInitialized() = runTest {
        verify(exactly(1)) {
            gameUseCase.restoreGameState(any())
        }
    }

    @Test
    fun initializeWithAIModeAndRestoreCorrectGameState() = runTest {
        val aiGameViewModel = GameViewModel(
            gameUseCase,
            SavedStateHandle(),
            Game(PLAYER_VS_AI)
        )

        verify(exactly(2)) { // Called once in setup(), once here
            gameUseCase.restoreGameState(any())
        }
    }

    @Test
    fun callReplayGameInAIModeWhenReplayEventReceived() = runTest {
        val aiGameViewModel = GameViewModel(
            gameUseCase,
            SavedStateHandle(),
            Game(PLAYER_VS_AI)
        )

        aiGameViewModel.handleEvent(GameEvent.ReplayClicked)
        verifySuspend(exactly(1)) {
            gameUseCase.replayGame()
        }
    }

    @Test
    fun callRestartGameInAIModeWhenRestartEventReceived() = runTest {
        val aiGameViewModel = GameViewModel(
            gameUseCase,
            SavedStateHandle(),
            Game(PLAYER_VS_AI)
        )

        aiGameViewModel.handleEvent(GameEvent.RestartClicked)
        verifySuspend(exactly(1)) {
            gameUseCase.restartGame()
        }
    }

    @Test
    fun callCellClickedInAIModeWhenCellClickedEventReceived() = runTest {
        val aiGameViewModel = GameViewModel(
            gameUseCase,
            SavedStateHandle(),
            Game(PLAYER_VS_AI)
        )

        aiGameViewModel.handleEvent(GameEvent.CellClicked(5))
        verifySuspend(exactly(1)) {
            gameUseCase.clickOnCell(5)
        }
    }

    @Test
    fun handleMultipleCellClicksInAIMode() = runTest {
        val aiGameViewModel = GameViewModel(
            gameUseCase,
            SavedStateHandle(),
            Game(PLAYER_VS_AI)
        )

        aiGameViewModel.handleEvent(GameEvent.CellClicked(0))
        aiGameViewModel.handleEvent(GameEvent.CellClicked(4))
        aiGameViewModel.handleEvent(GameEvent.CellClicked(8))

        verifySuspend(exactly(1)) {
            gameUseCase.clickOnCell(0)
        }
        verifySuspend(exactly(1)) {
            gameUseCase.clickOnCell(4)
        }
        verifySuspend(exactly(1)) {
            gameUseCase.clickOnCell(8)
        }
    }

    @Test
    fun handleRulesClickedInAIMode() = runTest {
        val aiGameViewModel = GameViewModel(
            gameUseCase,
            SavedStateHandle(),
            Game(PLAYER_VS_AI)
        )

        // Should not crash or throw exception
        aiGameViewModel.handleEvent(GameEvent.RulesClicked)
        
        // Verify no unexpected interactions with game use case
        verifySuspend(exactly(0)) {
            gameUseCase.clickOnCell(any())
        }
        verifySuspend(exactly(0)) {
            gameUseCase.replayGame()
        }
        verifySuspend(exactly(0)) {
            gameUseCase.restartGame()
        }
    }

    @Test
    fun initializeWithPlayerVsPlayerModeCorrectly() = runTest {
        val pvpGameViewModel = GameViewModel(
            gameUseCase,
            SavedStateHandle(),
            Game(PLAYER_VS_PLAYER)
        )

        verify(exactly(2)) { // Called once in setup(), once here
            gameUseCase.restoreGameState(any())
        }
    }

    @Test
    fun handleSequentialEventsInAIMode() = runTest {
        val aiGameViewModel = GameViewModel(
            gameUseCase,
            SavedStateHandle(),
            Game(PLAYER_VS_AI)
        )

        // Simulate a game sequence
        aiGameViewModel.handleEvent(GameEvent.CellClicked(0))
        aiGameViewModel.handleEvent(GameEvent.CellClicked(1))
        aiGameViewModel.handleEvent(GameEvent.ReplayClicked)
        aiGameViewModel.handleEvent(GameEvent.CellClicked(4))
        aiGameViewModel.handleEvent(GameEvent.RestartClicked)

        verifySuspend(exactly(1)) {
            gameUseCase.clickOnCell(0)
        }
        verifySuspend(exactly(1)) {
            gameUseCase.clickOnCell(1)
        }
        verifySuspend(exactly(1)) {
            gameUseCase.clickOnCell(4)
        }
        verifySuspend(exactly(1)) {
            gameUseCase.replayGame()
        }
        verifySuspend(exactly(1)) {
            gameUseCase.restartGame()
        }
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }
}