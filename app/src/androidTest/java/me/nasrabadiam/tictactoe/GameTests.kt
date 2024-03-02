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
import me.nasrabadiam.tictactoe.game.Players
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

        assertCountEquals(Players.X.toString(), 1)
    }

    @Test
    fun whenClickOnSecondCellShouldChangeTurn(): Unit = with(composeRule) {
        setContent { MainScreen(gameUseCase) }

        clickOnFirstEmptyCell()
        clickOnFirstEmptyCell()

        assertCountEquals(Players.X.toString(), 1)
        assertCountEquals(Players.O.toString(), 1)
    }

    @Test
    fun whenClickOnCellShouldChangeTurnEveryTime(): Unit = with(composeRule) {
        setContent { MainScreen(gameUseCase) }

        repeat(DEFAULT_BOARD_CELL_COUNT) {
            clickOnFirstEmptyCell()

            val xCounts = (it / 2) + 1
            assertCountEquals(Players.X.toString(), xCounts)

            val oCounts = (it + 1) / 2
            assertCountEquals(Players.O.toString(), oCounts)
        }
    }

    @Test
    fun whenClickOnCellThatClickedBeforeShouldDoNothing(): Unit = with(composeRule) {
        setContent { MainScreen(gameUseCase) }

        clickOnFirstEmptyCell()

        assertCountEquals(Players.X.toString(), 1)

        clickOnXCell()

        assertCountEquals(Players.X.toString(), 1)
    }

    /**
     * X - D - D
     * X - D - D
     * X - D - D
     */
    @Test
    fun whenXWinsVerticallyShouldShowWinnerAndEndsTheGame(): Unit = with(composeRule) {
        setContent { MainScreen(gameUseCase) }

        clickOnCell(0, 0) // X
        clickOnCell(1, 0) // O

        clickOnCell(0, 1) // X
        clickOnCell(1, 1) // O

        clickOnCell(0, 2) // X

        onNode(hasText("X Wins")).isDisplayed()

        clickOnCell(2, 2) // should not show O on 2,2, because the game is finished!
        assertCountEquals(Players.O.toString(), 2)
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
            .filter(hasText(Players.X.toString()))
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
        private const val RESULT_TEST_TAG = "Result"
        private const val CELL_TEST_TAG = "cell"
        private const val GRID_TEST_TAG = "grid"
        private const val GAME_BOARD_TEST_TAG = "game_board"
    }
}