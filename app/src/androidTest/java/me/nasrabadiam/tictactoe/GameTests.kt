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
    fun whenClickOnCellShouldChangeTurnEveryTime(): Unit = with(composeRule) {
        setContent { MainScreen(gameUseCase) }

        repeat(DEFAULT_BOARD_CELL_COUNT) {
            clickOnFirstEmptyCell()

            val xCounts = (it / 2) + 1
            assertCountEquals(Player.X.toString(), xCounts)

            val oCounts = (it + 1) / 2
            assertCountEquals(Player.O.toString(), oCounts)
        }
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
     * X - D - D
     * X - D - D
     * X - D - D
     */
    @Test
    fun whenXWinsVerticallyShouldShowWinnerAndEndsTheGame(): Unit = with(composeRule) {
        setContent { MainScreen(gameUseCase) }

        clickOnCell(col = 0, row = 0) // X
        clickOnCell(col = 1, row = 0) // O

        clickOnCell(col = 0, row = 1) // X
        clickOnCell(col = 1, row = 1) // O

        clickOnCell(col = 0, row = 2) // X

        // assert showing winner
        onNode(hasText("X Wins")).isDisplayed()

        // assert game ends
        clickOnCell(col = 2, row = 2) // should not show O on 2,2, because the game is finished!
        assertCountEquals(Player.O.toString(), 2)
    }

    /**
     * X - X - X
     * D - D - D
     * D - D - D
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
        onNode(hasText("X Wins")).assertIsDisplayed()

        // assert game ends
        clickOnCell(2, 2) // should not show O on 2,2, because the game is finished!
        assertCountEquals(Player.O.toString(), 2)
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
    }
}