package me.nasrabadiam.tictactoe

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.filter
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.performClick
import me.nasrabadiam.tictactoe.game.DEFAULT_BOARD_CELL_COUNT
import me.nasrabadiam.tictactoe.game.GameUseCase
import me.nasrabadiam.tictactoe.game.Player
import me.nasrabadiam.tictactoe.game.utlis.getCellIndex
import me.nasrabadiam.tictactoe.game.utlis.listOfEmptyCells
import org.junit.Ignore
import org.junit.Rule
import org.junit.Test

class GameTests {

    @get:Rule
    val composeRule = createComposeRule()

    private val gameUseCase = GameUseCase()

    private val listOfEmptyCells = listOfEmptyCells(DEFAULT_BOARD_CELL_COUNT)

    @Test
    @Ignore
    fun displayGameBoard(): Unit = with(composeRule) {
        setContent { MainScreen(gameUseCase) }

        onNode(hasTestTag(GRID_TEST_TAG)).assertIsDisplayed()
    }

    @Test
    fun displayGameCells(): Unit = with(composeRule) {
        setContent { MainScreen(gameUseCase) }
        for (index in 0..DEFAULT_BOARD_CELL_COUNT) {
            onNode(hasTestTag(getCellTestTag(index))).isDisplayed()
        }
    }

    @Test
    fun whenClickOnCellsShouldDrawItem(): Unit = with(composeRule) {
        setContent { MainScreen(gameUseCase) }

        clickOnFirstEmptyCell()

        assertCountEquals(Player.X.toString(), 1)
    }

    @Test
    fun whenClickOnSecondCellShouldChangeTurn(): Unit = with(composeRule) {
        setContent { MainScreen(gameUseCase) }

        clickOnFirstEmptyCell()
        clickOnFirstEmptyCell()

        assertCountEquals(Player.X.toString(), 1)
        assertCountEquals(Player.O.toString(), 1)
    }

    @Test
    fun whenClickOnCellThatClickedBeforeShouldDoNothing(): Unit = with(composeRule) {
        setContent { MainScreen(gameUseCase) }

        clickOnFirstEmptyCell()

        assertCountEquals(Player.X.toString(), 1)

        clickOnXCell()

        assertCountEquals(Player.X.toString(), 1)
    }

    /**
     * X - M - M
     * X - M - M
     * X - M - M
     */
    @Test
    fun whenXWinsVerticallyShouldShowWinnerAndEndsTheGame(): Unit = with(composeRule) {
        setContent { MainScreen(gameUseCase) }

        val colIndex  = 0
        clickOnCell(col = colIndex, row = 0) // X
        clickOnCell(col = 1, row = 0) // O

        clickOnCell(col = colIndex, row = 1) // X
        clickOnCell(col = 1, row = 1) // O

        clickOnCell(col = colIndex, row = 2) // X

        // assert showing winner
        onNode(hasText(X_WINS_RESULT_STRING)).isDisplayed()

        // assert game ends
        clickOnCell(col = 2, row = 2) // should not show O on 2,2, because the game is finished!
        assertCountEquals(Player.O.toString(), 2)
    }

    /**
     * X - X - X
     * M - M - M
     * M - M - M
     */
    @Test
    fun whenXWinsHorizontallyShouldShowWinnerAndEndsTheGame(): Unit = with(composeRule) {
        setContent { MainScreen(gameUseCase) }

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
        assertCountEquals(Player.O.toString(), 2)
    }

    /**
     * X - M - M
     * M - X - M
     * M - M - X
     */
    @Test
    fun whenXWinsInCrossShouldShowWinnerAndEndsTheGame(): Unit = with(composeRule) {
        setContent { MainScreen(gameUseCase) }

        clickOnCell(col = 0, row = 0) // X
        clickOnCell(col = 0, row = 1) // O

        clickOnCell(col = 1, row = 1) // X
        clickOnCell(col = 0, row = 2) // O

        clickOnCell(col = 2, row = 2) // X

        // assert showing winner
        onNode(hasText(X_WINS_RESULT_STRING)).assertIsDisplayed()

        // assert game ends
        clickOnCell(col = 1, row = 0) // should not show O on 1,0, because the game is finished!
        assertCountEquals(Player.O.toString(), 2)
    }


    /**
     * M - M - O
     * M - M - O
     * M - M - O
     */
    @Test
    fun whenOWinsVerticallyShouldShowWinnerAndEndsTheGame(): Unit = with(composeRule) {
        setContent { MainScreen(gameUseCase) }

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
        assertCountEquals(Player.X.toString(), 3)
    }

    /**
     * M - M - M
     * O - O - O
     * M - M - M
     */
    @Test
    fun whenOWinsHorizontallyShouldShowWinnerAndEndsTheGame(): Unit = with(composeRule) {
        setContent { MainScreen(gameUseCase) }

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
        assertCountEquals(Player.X.toString(), 3)
    }

    /**
     * M - M - O
     * M - O - M
     * O - M - M
     */
    @Test
    fun whenOWinsInCrossShouldShowWinnerAndEndsTheGame(): Unit = with(composeRule) {
        setContent { MainScreen(gameUseCase) }

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
        assertCountEquals(Player.X.toString(), 3)
    }

    private fun ComposeContentTestRule.assertCountEquals(
        text: String,
        counts: Int
    ) {
        onNode(hasTestTag(GAME_BOARD_TEST_TAG))
            .onChildren()
            .filter(hasClickAction())
            .filter(hasText(text))
            .assertCountEquals(counts)
    }

    private fun ComposeContentTestRule.clickOnXCell() {
        onNode(hasTestTag(GAME_BOARD_TEST_TAG))
            .onChildren()
            .filter(hasClickAction())
            .filter(hasText(Player.X.toString()))
            .onFirst()
            .performClick()
    }

    private fun ComposeContentTestRule.clickOnFirstEmptyCell() {
        onNode(hasTestTag(GAME_BOARD_TEST_TAG))
            .onChildren()
            .filter(hasClickAction())
            .filter(hasText(""))
            .onFirst()
            .performClick()
    }

    private fun ComposeContentTestRule.clickOnCell(
        col: Int,
        row: Int
    ) {
        val cellIndex = listOfEmptyCells.getCellIndex(row = row, col = col)
        onNode(hasTestTag(GAME_BOARD_TEST_TAG))
            .onChildren()
            .filter(hasClickAction())
            .filter(hasTestTag(getCellTestTag(cellIndex)))
            .onFirst()
            .performClick()
    }

    private fun getCellTestTag(index: Int): String {
        return CELL_TEST_TAG + "_$index"
    }

    companion object {
        private const val CELL_TEST_TAG = "cell"
        private const val GRID_TEST_TAG = "grid"
        private const val GAME_BOARD_TEST_TAG = "game_board"
        private const val X_WINS_RESULT_STRING = "X Wins"
        private const val O_WINS_RESULT_STRING = "O Wins"
    }
}