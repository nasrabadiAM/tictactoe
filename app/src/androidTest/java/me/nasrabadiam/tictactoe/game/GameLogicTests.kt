package me.nasrabadiam.tictactoe.game

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.lifecycle.SavedStateHandle
import me.nasrabadiam.tictactoe.DRAW_RESULT_STRING
import me.nasrabadiam.tictactoe.WINNER_CLICKABLE_CONTENT_DESCRIPTION
import me.nasrabadiam.tictactoe.WINNER_RESULT_STRING
import me.nasrabadiam.tictactoe.assertPlayersCountEquals
import me.nasrabadiam.tictactoe.clickOnCell
import me.nasrabadiam.tictactoe.game.model.Player
import me.nasrabadiam.tictactoe.game.ui.GameScreen
import me.nasrabadiam.tictactoe.game.ui.GameViewModel
import me.nasrabadiam.tictactoe.ui.getWindowSizeClass
import org.junit.Rule
import org.junit.Test

class GameLogicTests {

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

    /**
     * X - M - M
     * X - M - M
     * X - M - M
     */
    @Test
    fun whenXWinsVerticallyShouldShowWinnerAndEndsTheGame(): Unit = with(composeRule) {
        setContent()

        val colIndex = 0
        clickOnCell(col = colIndex, row = 0) // X
        clickOnCell(col = 1, row = 0) // O

        clickOnCell(col = colIndex, row = 1) // X
        clickOnCell(col = 1, row = 1) // O

        clickOnCell(col = colIndex, row = 2) // X

        // assert showing winner
        onNode(hasText(WINNER_RESULT_STRING)).isDisplayed()
        onNode(hasContentDescription("X $WINNER_CLICKABLE_CONTENT_DESCRIPTION"))
    }

    /**
     * X - X - X
     * M - M - M
     * M - M - M
     */
    @Test
    fun whenXWinsHorizontallyShouldShowWinnerAndEndsTheGame(): Unit = with(composeRule) {
        setContent()

        val rowIndex = 0
        clickOnCell(col = 0, row = rowIndex) // X
        clickOnCell(col = 1, row = 1) // O

        clickOnCell(col = 1, row = rowIndex) // X
        clickOnCell(col = 0, row = 1) // O

        clickOnCell(col = 2, row = rowIndex) // X

        // assert showing winner
        onNode(hasText(WINNER_RESULT_STRING)).isDisplayed()
        onNode(hasContentDescription("X $WINNER_CLICKABLE_CONTENT_DESCRIPTION"))
    }

    /**
     * X - M - M
     * M - X - M
     * M - M - X
     */
    @Test
    fun whenXWinsInCrossShouldShowWinnerAndEndsTheGame(): Unit = with(composeRule) {
        setContent()

        clickOnCell(col = 0, row = 0) // X
        clickOnCell(col = 0, row = 1) // O

        clickOnCell(col = 1, row = 1) // X
        clickOnCell(col = 0, row = 2) // O

        clickOnCell(col = 2, row = 2) // X

        // assert showing winner
        onNode(hasText(WINNER_RESULT_STRING)).isDisplayed()
        onNode(hasContentDescription("X $WINNER_CLICKABLE_CONTENT_DESCRIPTION"))
    }

    /**
     * M - M - O
     * M - M - O
     * M - M - O
     */
    @Test
    fun whenOWinsVerticallyShouldShowWinnerAndEndsTheGame(): Unit = with(composeRule) {
        setContent()

        val colIndex = 2
        clickOnCell(col = 0, row = 0) // X
        clickOnCell(col = colIndex, row = 0) // O

        clickOnCell(col = 0, row = 1) // X
        clickOnCell(col = colIndex, row = 1) // O

        clickOnCell(col = 1, row = 1) // X
        clickOnCell(col = colIndex, row = 2) // O

        // assert showing winner
        onNode(hasText(WINNER_RESULT_STRING)).isDisplayed()
        onNode(hasContentDescription("O $WINNER_CLICKABLE_CONTENT_DESCRIPTION"))
    }

    /**
     * M - M - M
     * O - O - O
     * M - M - M
     */
    @Test
    fun whenOWinsHorizontallyShouldShowWinnerAndEndsTheGame(): Unit = with(composeRule) {
        setContent()

        val rowIndex = 1
        clickOnCell(col = 0, row = 0) // X
        clickOnCell(col = 0, row = rowIndex) // O

        clickOnCell(col = 1, row = 0) // X
        clickOnCell(col = 1, row = rowIndex) // O

        clickOnCell(col = 2, row = 2) // X
        clickOnCell(col = 2, row = rowIndex) // O

        // assert showing winner
        onNode(hasText(WINNER_RESULT_STRING)).isDisplayed()
        onNode(hasContentDescription("O $WINNER_CLICKABLE_CONTENT_DESCRIPTION"))
    }

    /**
     * M - M - O
     * M - O - M
     * O - M - M
     */
    @Test
    fun whenOWinsInCrossShouldShowWinnerAndEndsTheGame(): Unit = with(composeRule) {
        setContent()

        clickOnCell(col = 0, row = 0) // X
        clickOnCell(col = 2, row = 0) // O

        clickOnCell(col = 0, row = 1) // X
        clickOnCell(col = 1, row = 1) // O

        clickOnCell(col = 2, row = 2) // X
        clickOnCell(col = 0, row = 2) // O

        // assert showing winner
        onNode(hasText(WINNER_RESULT_STRING)).isDisplayed()
        onNode(hasContentDescription("O $WINNER_CLICKABLE_CONTENT_DESCRIPTION"))
    }

    /**
     * X - O - O
     * O - X - X
     * X - X - O
     */
    @Test
    fun whenOAndXDrawShouldShowDrawAndEndsTheGame(): Unit = with(composeRule) {
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

        // assert showing draw
        onNode(hasText(DRAW_RESULT_STRING)).assertIsDisplayed()

        // assert game ends
        assertPlayersCountEquals(Player.X.toString(), 5)
        assertPlayersCountEquals(Player.O.toString(), 4)
    }
}