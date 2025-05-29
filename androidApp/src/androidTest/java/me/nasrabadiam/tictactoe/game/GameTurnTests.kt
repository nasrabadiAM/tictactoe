package me.nasrabadiam.tictactoe.game

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.lifecycle.SavedStateHandle
import me.nasrabadiam.tictactoe.GameWindowSizeClass.COMPACT
import me.nasrabadiam.tictactoe.REPLAY_GAME_BUTTON_TEXT
import me.nasrabadiam.tictactoe.assertPlayersCountEquals
import me.nasrabadiam.tictactoe.clickOnCell
import me.nasrabadiam.tictactoe.game.model.Game
import me.nasrabadiam.tictactoe.game.model.GameMode.PLAYER_VS_PLAYER
import me.nasrabadiam.tictactoe.game.model.Player
import me.nasrabadiam.tictactoe.game.ui.GameScreen
import me.nasrabadiam.tictactoe.game.ui.GameViewModel
import org.junit.Rule
import org.junit.Test

class GameTurnTests {

    @get:Rule
    val composeRule = createComposeRule()

    private val gameUseCase = GameUseCase()
    private val gameViewModel = GameViewModel(
        gameUseCase,
        SavedStateHandle(),
        Game(PLAYER_VS_PLAYER),
    )
    private val windowClass = COMPACT

    @Test
    fun whenGameStartsXShouldStartTheGame(): Unit = with(composeRule) {
        setContent { GameScreen(gameViewModel, windowClass) }

        clickOnCell(0, 0)

        assertPlayersCountEquals(Player.X.toString(), 1)
        assertPlayersCountEquals(Player.O.toString(), 0)
    }

    @Test
    fun whenGameDrawsWhoDoesNotPlayThisGameFirstShouldPlayFirst(): Unit = with(composeRule) {
        setContent { GameScreen(gameViewModel, windowClass) }

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
    fun whenXWinsTheGameShouldStartTheNextGame(): Unit = with(composeRule) {
        setContent { GameScreen(gameViewModel, windowClass) }

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