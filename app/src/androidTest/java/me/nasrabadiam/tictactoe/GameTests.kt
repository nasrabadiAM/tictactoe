package me.nasrabadiam.tictactoe

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import me.nasrabadiam.tictactoe.game.GameUseCase
import me.nasrabadiam.tictactoe.game.model.DEFAULT_BOARD_CELL_COUNT
import me.nasrabadiam.tictactoe.game.model.Player
import org.junit.Rule
import org.junit.Test

class GameTests {

    @get:Rule
    val composeRule = createComposeRule()

    private val gameUseCase = GameUseCase()
    private val isExpandedScreen = false

    @Test
    fun displayGameCells(): Unit = with(composeRule) {
        setContent { MainScreen(gameUseCase, isExpandedScreen) }
        for (index in 0..DEFAULT_BOARD_CELL_COUNT) {
            onNode(hasTestTag(getCellTestTag(index))).isDisplayed()
        }
    }

    @Test
    fun displayRestartButton(): Unit = with(composeRule) {
        setContent { MainScreen(gameUseCase, isExpandedScreen) }

        onNodeWithText(RESTART_GAME_BUTTON_TEXT).assertIsDisplayed()
    }

    @Test
    fun whenClickOnRestartGameButtonShouldRestartGame(): Unit = with(composeRule) {
        setContent { MainScreen(gameUseCase, isExpandedScreen) }

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
        assertCountEquals(Player.X.toString(), 0)
        assertCountEquals(Player.O.toString(), 0)

        // assert any game result not to be shown
        onNode(hasText(DRAW_RESULT_STRING)).assertIsNotDisplayed()
        onNode(hasText(X_WINS_RESULT_STRING)).assertIsNotDisplayed()
        onNode(hasText(O_WINS_RESULT_STRING)).assertIsNotDisplayed()
    }
}