package me.nasrabadiam.tictactoe.ui.game

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertAny
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.runComposeUiTest
import androidx.lifecycle.SavedStateHandle
import me.nasrabadiam.tictactoe.GameWindowSizeClass.COMPACT
import me.nasrabadiam.tictactoe.game.GameUseCase
import me.nasrabadiam.tictactoe.game.ui.GameScreenRoute
import me.nasrabadiam.tictactoe.game.ui.GameViewModel
import me.nasrabadiam.tictactoe.ui.REPLAY_GAME_BUTTON_TEXT
import me.nasrabadiam.tictactoe.ui.clickOnCell
import kotlin.test.Test

@OptIn(ExperimentalTestApi::class)
class GameScoreTests {

    private val gameUseCase = GameUseCase()
    private val gameViewModel = GameViewModel(gameUseCase, SavedStateHandle())
    private val windowClass = COMPACT

    @Test
    fun whenXWinsOneTimeShouldShowScore1AndO0() = runComposeUiTest {
        setContent { GameScreenRoute(gameViewModel, windowClass) }

        val rowIndex = 0
        clickOnCell(col = 0, row = rowIndex) // X
        clickOnCell(col = 1, row = 1) // O

        clickOnCell(col = 1, row = rowIndex) // X
        clickOnCell(col = 0, row = 1) // O

        clickOnCell(col = 2, row = rowIndex) // X

        onNodeWithContentDescription(
            label = "X Score is",
            substring = true
        ).onChildren().assertAny(hasText("1"))
    }

    @Test
    fun whenXWinsOneTimeAndOWinsAnotherShouldShowScore1ForXAnd1ForO() = runComposeUiTest {
        setContent { GameScreenRoute(gameViewModel, windowClass) }

        val rowIndex = 0
        clickOnCell(col = 0, row = rowIndex) // X
        clickOnCell(col = 1, row = 1) // O

        clickOnCell(col = 1, row = rowIndex) // X
        clickOnCell(col = 0, row = 1) // O

        clickOnCell(col = 2, row = rowIndex) // X wins

        // assert showing x score
        onNodeWithContentDescription(
            label = "X Score is",
            substring = true
        ).onChildren().assertAny(hasText("1"))

        // replay game
        onNodeWithText(REPLAY_GAME_BUTTON_TEXT).performClick()

        // it is o turn
        val colIndex = 2
        clickOnCell(col = 0, row = 0) // X
        clickOnCell(col = colIndex, row = 0) // O

        clickOnCell(col = 0, row = 1) // X
        clickOnCell(col = colIndex, row = 1) // O

        clickOnCell(col = 1, row = 1) // X
        clickOnCell(col = colIndex, row = 2) // O wins

        // assert showing scores
        onNodeWithContentDescription(
            label = "X Score is",
            substring = true
        ).onChildren().assertAny(hasText("1"))
        onNodeWithContentDescription(
            label = "O Score is",
            substring = true
        ).onChildren().assertAny(hasText("1"))
    }
}