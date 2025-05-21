package me.nasrabadiam.tictactoe.ui.game

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.runComposeUiTest
import androidx.lifecycle.SavedStateHandle
import me.nasrabadiam.tictactoe.GameWindowSizeClass.COMPACT
import me.nasrabadiam.tictactoe.game.GameUseCase
import me.nasrabadiam.tictactoe.game.model.Player
import me.nasrabadiam.tictactoe.game.ui.GameScreenRoute
import me.nasrabadiam.tictactoe.game.ui.GameViewModel
import me.nasrabadiam.tictactoe.ui.REPLAY_GAME_BUTTON_TEXT
import me.nasrabadiam.tictactoe.ui.assertPlayersCountEquals
import me.nasrabadiam.tictactoe.ui.clickOnCell
import kotlin.test.Test

@OptIn(ExperimentalTestApi::class)
class GameTurnTests {

    private val gameUseCase = GameUseCase()
    private val gameViewModel = GameViewModel(gameUseCase, SavedStateHandle())
    private val windowClass = COMPACT

    @Test
    fun whenGameStartsXShouldStartTheGame() = runComposeUiTest {
        setContent { GameScreenRoute(gameViewModel, windowClass) }

        clickOnCell(0, 0)

        assertPlayersCountEquals(Player.X.toString(), 1)
        assertPlayersCountEquals(Player.O.toString(), 0)
    }

    @Test
    fun whenGameDrawsWhoDoesNotPlayThisGameFirstShouldPlayFirst() = runComposeUiTest {
        setContent { GameScreenRoute(gameViewModel, windowClass) }

        clickOnCell(col = 0, row = 0) // X
        clickOnCell(col = 2, row = 0) // O

        clickOnCell(col = 1, row = 1) // X
        clickOnCell(col = 0, row = 1) // O

        clickOnCell(col = 0, row = 2) // X
        clickOnCell(col = 2, row = 2) // O

        clickOnCell(col = 2, row = 1) // X
        clickOnCell(col = 1, row = 0) // O

        clickOnCell(col = 1, row = 2) // X

        onNodeWithText(REPLAY_GAME_BUTTON_TEXT).performClick()

        clickOnCell(col = 1, row = 2) // O

        assertPlayersCountEquals(Player.X.toString(), 0)
        assertPlayersCountEquals(Player.O.toString(), 1)
    }

    @Test
    fun whenXWinsTheGameShouldStartTheNextGame() = runComposeUiTest {
        setContent { GameScreenRoute(gameViewModel, windowClass) }

        val colIndex = 0

        clickOnCell(col = colIndex, row = 0) // X
        clickOnCell(col = 1, row = 0) // O

        clickOnCell(col = colIndex, row = 1) // X
        clickOnCell(col = 1, row = 1) // O

        clickOnCell(col = colIndex, row = 2) // X wins

        onNodeWithText(REPLAY_GAME_BUTTON_TEXT).performClick()

        clickOnCell(col = 1, row = 2) // X

        assertPlayersCountEquals(Player.X.toString(), 1)
        assertPlayersCountEquals(Player.O.toString(), 0)
    }
}