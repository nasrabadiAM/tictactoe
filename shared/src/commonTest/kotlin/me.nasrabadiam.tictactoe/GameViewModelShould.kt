package me.nasrabadiam.tictactoe

import androidx.lifecycle.SavedStateHandle
import dev.mokkery.MockMode.original
import dev.mokkery.answering.calls
import dev.mokkery.answering.returns
import dev.mokkery.every
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock
import dev.mokkery.verify
import dev.mokkery.verify.VerifyMode.Companion.exactly
import dev.mokkery.verifySuspend
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import me.nasrabadiam.tictactoe.game.GameUseCase
import me.nasrabadiam.tictactoe.game.ai.TicTacToeAI
import me.nasrabadiam.tictactoe.game.model.Game
import me.nasrabadiam.tictactoe.game.model.GameMode
import me.nasrabadiam.tictactoe.game.model.Player
import me.nasrabadiam.tictactoe.game.ui.GameEvent
import me.nasrabadiam.tictactoe.game.ui.GameViewModel
import me.nasrabadiam.tictactoe.test.mock.Mockable
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Ignore
import kotlin.test.Test

@OptIn(ExperimentalCoroutinesApi::class)
@Mockable
class GameViewModelShould {

    private val testDispatcher: TestDispatcher = StandardTestDispatcher()

    private lateinit var gameUseCase: GameUseCase

    private lateinit var gameViewModel: GameViewModel
    private lateinit var ticTacToeAI: TicTacToeAI

    @BeforeTest
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        ticTacToeAI = TicTacToeAI(
            defaultDispatcher = testDispatcher
        )
        gameUseCase = mock<GameUseCase>(original) {
            every { currentPlayer.onEach(any()) } calls { MutableStateFlow(Player.X) }
            everySuspend { replayGame() } calls {}
            every { ai } returns ticTacToeAI
        }
        gameViewModel = GameViewModel(
            gameUseCase,
            SavedStateHandle(),
            Game(GameMode.PlayWithFriend),
        )
    }

    @Test
    fun callReplayGameWhenReplayEventReceived() = runTest(testDispatcher) {

        gameViewModel.handleEvent(GameEvent.ReplayClicked)
        testScheduler.advanceUntilIdle()
        verifySuspend(exactly(1)) {
            gameUseCase.replayGame()
        }
    }

    @Test
    @Ignore
    fun callRestartGameWhenRestartEventReceived() = runTest {

        gameViewModel.handleEvent(GameEvent.RestartClicked)
        verifySuspend(exactly(1)) {
            gameUseCase.restartGame()
        }
    }

    @Test
    fun callCellClickedWhenCellClickedEventReceived() = runTest(testDispatcher) {

        gameViewModel.handleEvent(GameEvent.CellClicked(1))
        testScheduler.advanceUntilIdle()
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
            Game(GameMode.PlayWithAI())
        )

        verify(exactly(2)) { // Called once in setup(), once here
            gameUseCase.restoreGameState(any())
        }
    }

    @Test
    fun callReplayGameInAIModeWhenReplayEventReceived() = runTest(testDispatcher) {
        val aiGameViewModel = GameViewModel(
            gameUseCase,
            SavedStateHandle(),
            Game(GameMode.PlayWithAI())
        )

        aiGameViewModel.handleEvent(GameEvent.ReplayClicked)
        testScheduler.advanceUntilIdle()
        verifySuspend(exactly(1)) {
            gameUseCase.replayGame()
        }
    }

    @Test
    fun callRestartGameInAIModeWhenRestartEventReceived() = runTest(testDispatcher) {
        val aiGameViewModel = GameViewModel(
            gameUseCase,
            SavedStateHandle(),
            Game(GameMode.PlayWithAI())
        )

        aiGameViewModel.handleEvent(GameEvent.RestartClicked)
        testScheduler.advanceUntilIdle()
        verifySuspend(exactly(1)) {
            gameUseCase.restartGame()
        }
    }

    @Test
    fun callCellClickedInAIModeWhenCellClickedEventReceived() = runTest(testDispatcher) {
        val gameUseCase = mock<GameUseCase>(original) {
            every { currentPlayer.onEach(any()) } calls { MutableStateFlow(Player.X) }
            everySuspend { replayGame() } calls {}
            every { ai } returns ticTacToeAI
        }
        val aiGameViewModel = GameViewModel(
            gameUseCase,
            SavedStateHandle(),
            Game(GameMode.PlayWithAI())
        )

        aiGameViewModel.handleEvent(GameEvent.CellClicked(5))
        testScheduler.advanceUntilIdle()
        verifySuspend(exactly(1)) {
            gameUseCase.clickOnCell(5)
        }
    }

    @Test
    fun handleMultipleCellClicksInAIMode() = runTest(testDispatcher) {
        val aiGameViewModel = GameViewModel(
            gameUseCase,
            SavedStateHandle(),
            Game(GameMode.PlayWithAI())
        )

        aiGameViewModel.handleEvent(GameEvent.CellClicked(0))
        aiGameViewModel.handleEvent(GameEvent.CellClicked(4))
        aiGameViewModel.handleEvent(GameEvent.CellClicked(8))
        testScheduler.advanceUntilIdle()

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
            Game(GameMode.PlayWithAI())
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
        GameViewModel(
            gameUseCase,
            SavedStateHandle(),
            Game(GameMode.PlayWithFriend)
        )

        verify(exactly(2)) { // Called once in setup(), once here
            gameUseCase.restoreGameState(any())
        }
    }

    @Test
    fun handleSequentialEventsInAIMode() = runTest(testDispatcher) {

        val aiGameViewModel = GameViewModel(
            gameUseCase,
            SavedStateHandle(),
            Game(GameMode.PlayWithAI())
        )

        // Simulate a game sequence
        aiGameViewModel.handleEvent(GameEvent.CellClicked(0))
        aiGameViewModel.handleEvent(GameEvent.CellClicked(1))
        aiGameViewModel.handleEvent(GameEvent.ReplayClicked)
        aiGameViewModel.handleEvent(GameEvent.CellClicked(4))
        aiGameViewModel.handleEvent(GameEvent.RestartClicked)
        testScheduler.advanceUntilIdle()

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