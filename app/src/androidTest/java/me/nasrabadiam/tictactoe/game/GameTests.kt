package me.nasrabadiam.tictactoe.game

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.lifecycle.SavedStateHandle
import me.nasrabadiam.tictactoe.DRAW_RESULT_STRING
import me.nasrabadiam.tictactoe.REPLAY_GAME_BUTTON_TEXT
import me.nasrabadiam.tictactoe.RESTART_GAME_BUTTON_TEXT
import me.nasrabadiam.tictactoe.WINNER_RESULT_STRING
import me.nasrabadiam.tictactoe.assertPlayersCountEquals
import me.nasrabadiam.tictactoe.clickOnCell
import me.nasrabadiam.tictactoe.game.model.DEFAULT_BOARD_CELL_COUNT
import me.nasrabadiam.tictactoe.game.model.Player
import me.nasrabadiam.tictactoe.game.ui.GameScreen
import me.nasrabadiam.tictactoe.game.ui.GameViewModel
import me.nasrabadiam.tictactoe.getCellTestTag
import me.nasrabadiam.tictactoe.ui.getWindowSizeClass
import org.junit.Rule
import org.junit.Test

class GameTests {

    @get:Rule
    val composeRule = createComposeRule()

    private val gameUseCase = GameUseCase()
    private val gameViewModel = GameViewModel(gameUseCase, SavedStateHandle())

    private fun ComposeContentTestRule.setContent(
        viewModel: GameViewModel = gameViewModel
    ) {
        setContent {
            val windowSizeClass = getWindowSizeClass()
            GameScreen(viewModel, windowSizeClass)
        }
    }

    @Test
    fun displayGameCells(): Unit = with(composeRule) {
        setContent()
        for (index in 0..DEFAULT_BOARD_CELL_COUNT) {
            onNode(hasTestTag(getCellTestTag(index))).isDisplayed()
        }
    }

    @Test
    fun displayRestartButton(): Unit = with(composeRule) {
        setContent()

        onNodeWithText(RESTART_GAME_BUTTON_TEXT).assertIsDisplayed()
    }

    @Test
    fun displayReplayButtonWhenOneGameFinished(): Unit = with(composeRule) {
        setContent()

        onNodeWithText(REPLAY_GAME_BUTTON_TEXT).assertIsNotDisplayed()

        clickOnCell(col = 0, row = 0) // X
        clickOnCell(col = 2, row = 0) // O

        clickOnCell(col = 1, row = 1) // X
        clickOnCell(col = 0, row = 1) // O

        clickOnCell(col = 0, row = 2) // X
        clickOnCell(col = 2, row = 2) // O

        clickOnCell(col = 2, row = 1) // X
        clickOnCell(col = 1, row = 0) // O

        clickOnCell(col = 1, row = 2) // X  -> Draw

        onNodeWithText(REPLAY_GAME_BUTTON_TEXT).assertIsDisplayed()
    }

    @Test
    fun restartGameBoardWhenClickOnReplayButton(): Unit = with(composeRule) {
        setContent()

        clickOnCell(col = 0, row = 0) // X
        clickOnCell(col = 2, row = 0) // O

        clickOnCell(col = 1, row = 1) // X
        clickOnCell(col = 0, row = 1) // O

        clickOnCell(col = 0, row = 2) // X
        clickOnCell(col = 2, row = 2) // O

        clickOnCell(col = 2, row = 1) // X
        clickOnCell(col = 1, row = 0) // O

        clickOnCell(col = 1, row = 2) // X  -> Draw

        onNodeWithText(REPLAY_GAME_BUTTON_TEXT).performClick()

        onNodeWithText(REPLAY_GAME_BUTTON_TEXT).assertIsNotDisplayed()

        // assert game cells should be empty
        assertPlayersCountEquals(Player.X.toString(), 0)
        assertPlayersCountEquals(Player.O.toString(), 0)
    }

    @Test
    fun whenClickOnRestartGameButtonShouldRestartGame(): Unit = with(composeRule) {
        setContent()

        clickOnCell(col = 0, row = 0) // X
        clickOnCell(col = 2, row = 0) // O

        clickOnCell(col = 1, row = 1) // X
        clickOnCell(col = 0, row = 1) // O

        clickOnCell(col = 0, row = 2) // X
        clickOnCell(col = 2, row = 2) // O

        clickOnCell(col = 2, row = 1) // X
        clickOnCell(col = 1, row = 0) // O

        clickOnCell(col = 1, row = 2) // X

        val restartButton = onNodeWithText(RESTART_GAME_BUTTON_TEXT)
        restartButton.assertHasClickAction()
        restartButton.performClick()

        // assert game cells should be empty
        assertPlayersCountEquals(Player.X.toString(), 0)
        assertPlayersCountEquals(Player.O.toString(), 0)

        // assert any game result not to be shown
        onNode(hasText(DRAW_RESULT_STRING)).assertIsNotDisplayed()
        onNode(hasText(WINNER_RESULT_STRING)).assertIsNotDisplayed()
        onNode(hasText(WINNER_RESULT_STRING)).assertIsNotDisplayed()
        // all scores should disappear
        onNode(hasText("1")).assertIsNotDisplayed()
    }
}