package me.nasrabadiam.tictactoe

import androidx.lifecycle.SavedStateHandle
import dev.mokkery.MockMode.original
import dev.mokkery.matcher.any
import dev.mokkery.mock
import dev.mokkery.verify
import dev.mokkery.verify.VerifyMode.Companion.exactly
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import me.nasrabadiam.tictactoe.game.GameUseCase
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
        gameViewModel = GameViewModel(gameUseCase, SavedStateHandle())
    }

    @Test
    fun callReplayGameWhenReplayEventReceived() = runTest {

        gameViewModel.handleEvent(GameEvent.ReplayClicked)
        verify(exactly(1)) {
            gameUseCase.replayGame()
        }
    }

    @Test
    fun callRestartGameWhenRestartEventReceived() = runTest {

        gameViewModel.handleEvent(GameEvent.RestartClicked)
        verify(exactly(1)) {
            gameUseCase.restartGame()
        }
    }

    @Test
    fun callCellClickedWhenCellClickedEventReceived() = runTest {

        gameViewModel.handleEvent(GameEvent.CellClicked(1))
        verify(exactly(1)) {
            gameUseCase.clickOnCell(1)
        }
    }

    @Test
    fun callRestoreGameWhenViewModelInitialized() = runTest {
        verify(exactly(1)) {
            gameUseCase.restoreGameState(any())
        }
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }
}