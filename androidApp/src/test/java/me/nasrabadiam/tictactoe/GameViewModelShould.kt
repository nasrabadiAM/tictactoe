package me.nasrabadiam.tictactoe

import androidx.lifecycle.SavedStateHandle
import io.mockk.clearAllMocks
import io.mockk.spyk
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import me.nasrabadiam.tictactoe.game.ui.GameEvent
import me.nasrabadiam.tictactoe.game.GameUseCase
import me.nasrabadiam.tictactoe.game.ui.GameViewModel
import me.nasrabadiam.tictactoe.utils.MainDispatcherRule
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GameViewModelShould {

    @get:Rule
    val coroutineRule = MainDispatcherRule()

    private val gameUseCase: GameUseCase = spyk()
    private lateinit var gameViewModel: GameViewModel

    @Before
    fun setup() {
        gameViewModel = GameViewModel(gameUseCase, SavedStateHandle())
    }

    @Test
    fun callReplayGameWhenReplayEventReceived() = runTest {

        gameViewModel.handleEvent(GameEvent.ReplayClicked)
        verify(exactly = 1) {
            gameUseCase.replayGame()
        }
    }

    @Test
    fun callRestartGameWhenRestartEventReceived() = runTest {

        gameViewModel.handleEvent(GameEvent.RestartClicked)
        verify(exactly = 1) {
            gameUseCase.restartGame()
        }
    }

    @Test
    fun callCellClickedWhenCellClickedEventReceived() = runTest {

        gameViewModel.handleEvent(GameEvent.CellClicked(1))
        verify(exactly = 1) {
            gameUseCase.clickOnCell(1)
        }
    }

    @Test
    fun callRestoreGameWhenViewModelInitialized() = runTest {

        verify(exactly = 1) {
            gameUseCase.restoreGameState(any())
        }
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }
}