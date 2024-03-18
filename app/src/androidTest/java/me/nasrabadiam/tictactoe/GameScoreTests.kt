package me.nasrabadiam.tictactoe

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.printToLog
import me.nasrabadiam.tictactoe.game.GameUseCase
import org.junit.Rule
import org.junit.Test

class GameScoreTests {

    @get:Rule
    val composeRule = createComposeRule()

    private val gameUseCase = GameUseCase()
    private val isExpandedScreen = false

    @Test
    fun whenXWinsOneTimeShouldShowScore1AndO0(): Unit = with(composeRule) {
        setContent { MainScreen(gameUseCase, isExpandedScreen) }

        val rowIndex = 0
        clickOnCell(col = 0, row = rowIndex) // X
        clickOnCell(col = 1, row = 1) // O

        clickOnCell(col = 1, row = rowIndex) // X
        clickOnCell(col = 0, row = 1) // O

        clickOnCell(col = 2, row = rowIndex) // X

        // assert showing winner
        onNode(hasText("X: 1")).assertIsDisplayed()
    }

    @Test
    fun whenXWinsOneTimeAndOWinsAnotherShouldShowScore1ForXAnd1ForO(): Unit = with(composeRule) {
        setContent { MainScreen(gameUseCase, isExpandedScreen) }

        val rowIndex = 0
        clickOnCell(col = 0, row = rowIndex) // X
        clickOnCell(col = 1, row = 1) // O

        clickOnCell(col = 1, row = rowIndex) // X
        clickOnCell(col = 0, row = 1) // O

        clickOnCell(col = 2, row = rowIndex) // X wins

        // assert showing x score
        onNode(hasText("X: 1")).assertIsDisplayed()

        // replay game
        onNodeWithText(REPLAY_GAME_BUTTON_TEXT).performClick()

        // it is o turn
        val colIndex = 2
        clickOnCell(col = colIndex, row = 0) // O
        clickOnCell(col = 0, row = 0) // X

        clickOnCell(col = colIndex, row = 1) // O
        clickOnCell(col = 0, row = 1) // X

        clickOnCell(col = colIndex, row = 2) // O wins

        // assert showing scores
        onNode(hasText("X: 1")).assertIsDisplayed()
        onNode(hasText("O: 1")).assertIsDisplayed()
    }

    @Test
    fun whenDrawShouldShowDrawCount1(): Unit = with(composeRule) {
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

        onNode(hasText("Draw: 1")).assertIsDisplayed() // X and O Draw
    }
}