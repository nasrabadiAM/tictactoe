package me.nasrabadiam.tictactoe

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.filter
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.performClick
import me.nasrabadiam.tictactoe.game.DEFAULT_BOARD_SIZE
import me.nasrabadiam.tictactoe.game.GameUseCase
import me.nasrabadiam.tictactoe.game.Players
import org.junit.Ignore
import org.junit.Rule
import org.junit.Test

class GameTests {

    @get:Rule
    val composeRule = createComposeRule()

    private val gameUseCase = GameUseCase()

    @Test
    @Ignore
    fun displayGameBoard(): Unit = with(composeRule) {
        setContent { MainScreen(gameUseCase) }

        onNode(hasTestTag(GRID_TEST_TAG)).assertIsDisplayed()
    }

    @Test
    fun displayGameCells(): Unit = with(composeRule) {
        setContent { MainScreen(gameUseCase) }

        onAllNodes(hasTestTag(CELL_TEST_TAG)).assertCountEquals(DEFAULT_BOARD_SIZE)
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

        repeat(DEFAULT_BOARD_SIZE) {
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

    companion object {
        private const val CELL_TEST_TAG = "cell"
        private const val GRID_TEST_TAG = "grid"
        private const val GAME_BOARD_TEST_TAG = "game_board"
    }
}