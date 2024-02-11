package me.nasrabadiam.tictactoe

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule
import org.junit.Test

class GameTests {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun displayGameBoard(): Unit = with(composeRule) {
        setContent { TicTacToeGameBoard() }

        // (two line in each row * 3 row = 6) + 2 horizontal line = 8
        onAllNodes(hasTestTag("line")).assertCountEquals(8)
    }

    @Test
    fun displayGameCells(): Unit = with(composeRule) {
        setContent { TicTacToeGameBoard() }

        onAllNodes(hasTestTag("cell")).assertCountEquals(9)
    }
}