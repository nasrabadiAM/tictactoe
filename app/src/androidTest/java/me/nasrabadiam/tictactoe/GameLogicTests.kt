package me.nasrabadiam.tictactoe

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.lifecycle.SavedStateHandle
import me.nasrabadiam.tictactoe.GameWindowSizeClass.COMPACT
import me.nasrabadiam.tictactoe.game.GameUseCase
import me.nasrabadiam.tictactoe.game.model.Player
import org.junit.Rule
import org.junit.Test

class GameLogicTests {

    @get:Rule
    val composeRule = createComposeRule()

    private val gameUseCase = GameUseCase()
    private val gameViewModel = GameViewModel(gameUseCase, SavedStateHandle())
    private val windowClass = COMPACT

    /**
     * X - M - M
     * X - M - M
     * X - M - M
     */
    @Test
    fun whenXWinsVerticallyShouldShowWinnerAndEndsTheGame(): Unit = with(composeRule) {
        setContent { MainScreen(gameViewModel, windowClass) }

        val colIndex = 0
        clickOnCell(col = colIndex, row = 0) // X
        clickOnCell(col = 1, row = 0) // O

        clickOnCell(col = colIndex, row = 1) // X
        clickOnCell(col = 1, row = 1) // O

        clickOnCell(col = colIndex, row = 2) // X

        // assert showing winner
        onNode(hasText(X_WINS_RESULT_STRING)).isDisplayed()

        // assert game ends
        clickOnCell(col = 2, row = 2) // should not show O on 2,2, because the game is finished!
        assertPlayersCountEquals(Player.O.toString(), 2)
    }

    /**
     * X - X - X
     * M - M - M
     * M - M - M
     */
    @Test
    fun whenXWinsHorizontallyShouldShowWinnerAndEndsTheGame(): Unit = with(composeRule) {
        setContent { MainScreen(gameViewModel, windowClass) }

        val rowIndex = 0
        clickOnCell(col = 0, row = rowIndex) // X
        clickOnCell(col = 1, row = 1) // O

        clickOnCell(col = 1, row = rowIndex) // X
        clickOnCell(col = 0, row = 1) // O

        clickOnCell(col = 2, row = rowIndex) // X

        // assert showing winner
        onNode(hasText(X_WINS_RESULT_STRING)).assertIsDisplayed()

        // assert game ends
        clickOnCell(col = 2, row = 2) // should not show O on 2,2, because the game is finished!
        assertPlayersCountEquals(Player.O.toString(), 2)
    }

    /**
     * X - M - M
     * M - X - M
     * M - M - X
     */
    @Test
    fun whenXWinsInCrossShouldShowWinnerAndEndsTheGame(): Unit = with(composeRule) {
        setContent { MainScreen(gameViewModel, windowClass) }

        clickOnCell(col = 0, row = 0) // X
        clickOnCell(col = 0, row = 1) // O

        clickOnCell(col = 1, row = 1) // X
        clickOnCell(col = 0, row = 2) // O

        clickOnCell(col = 2, row = 2) // X

        // assert showing winner
        onNode(hasText(X_WINS_RESULT_STRING)).assertIsDisplayed()

        // assert game ends
        clickOnCell(col = 1, row = 0) // should not show O on 1,0, because the game is finished!
        assertPlayersCountEquals(Player.O.toString(), 2)
    }

    /**
     * M - M - O
     * M - M - O
     * M - M - O
     */
    @Test
    fun whenOWinsVerticallyShouldShowWinnerAndEndsTheGame(): Unit = with(composeRule) {
        setContent { MainScreen(gameViewModel, windowClass) }

        val colIndex = 2
        clickOnCell(col = 0, row = 0) // X
        clickOnCell(col = colIndex, row = 0) // O

        clickOnCell(col = 0, row = 1) // X
        clickOnCell(col = colIndex, row = 1) // O

        clickOnCell(col = 1, row = 1) // X
        clickOnCell(col = colIndex, row = 2) // O

        // assert showing winner
        onNode(hasText(O_WINS_RESULT_STRING)).isDisplayed()

        // assert game ends
        clickOnCell(col = 2, row = 1) // should not show X on 2,1, because the game is finished!
        assertPlayersCountEquals(Player.X.toString(), 3)
    }

    /**
     * M - M - M
     * O - O - O
     * M - M - M
     */
    @Test
    fun whenOWinsHorizontallyShouldShowWinnerAndEndsTheGame(): Unit = with(composeRule) {
        setContent { MainScreen(gameViewModel, windowClass) }

        val rowIndex = 1
        clickOnCell(col = 0, row = 0) // X
        clickOnCell(col = 0, row = rowIndex) // O

        clickOnCell(col = 1, row = 0) // X
        clickOnCell(col = 1, row = rowIndex) // O

        clickOnCell(col = 2, row = 2) // X
        clickOnCell(col = 2, row = rowIndex) // O

        // assert showing winner
        onNode(hasText(O_WINS_RESULT_STRING)).assertIsDisplayed()

        // assert game ends
        clickOnCell(col = 0, row = 2) // should not show X on 0,2, because the game is finished!
        assertPlayersCountEquals(Player.X.toString(), 3)
    }

    /**
     * M - M - O
     * M - O - M
     * O - M - M
     */
    @Test
    fun whenOWinsInCrossShouldShowWinnerAndEndsTheGame(): Unit = with(composeRule) {
        setContent { MainScreen(gameViewModel, windowClass) }

        clickOnCell(col = 0, row = 0) // X
        clickOnCell(col = 2, row = 0) // O

        clickOnCell(col = 0, row = 1) // X
        clickOnCell(col = 1, row = 1) // O

        clickOnCell(col = 2, row = 2) // X
        clickOnCell(col = 0, row = 2) // O

        // assert showing winner
        onNode(hasText(O_WINS_RESULT_STRING)).assertIsDisplayed()

        // assert game ends
        clickOnCell(col = 1, row = 0) // should not show X on 1,0, because the game is finished!
        assertPlayersCountEquals(Player.X.toString(), 3)
    }

    /**
     * X - O - O
     * O - X - X
     * X - X - O
     */
    @Test
    fun whenOAndXDrawShouldShowDrawAndEndsTheGame(): Unit = with(composeRule) {
        setContent { MainScreen(gameViewModel, windowClass) }

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