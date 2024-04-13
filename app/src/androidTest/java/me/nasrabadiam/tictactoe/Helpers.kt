package me.nasrabadiam.tictactoe

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.filter
import androidx.compose.ui.test.hasAnyChild
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.performClick
import me.nasrabadiam.tictactoe.game.model.Player
import me.nasrabadiam.tictactoe.game.model.utlis.getCellIndex

internal fun ComposeContentTestRule.assertPlayersCountEquals(
    testTag: String,
    counts: Int
) {
    onNode(hasTestTag(GAME_BOARD_TEST_TAG), useUnmergedTree = true)
        .onChildren()
        .filter(
            hasAnyChild(
                hasTestTag(testTag)
            ),
        )
        .assertCountEquals(counts)
}

internal fun ComposeContentTestRule.clickOnCell(
    col: Int,
    row: Int,
    player: Player? = null
) {
    val cellIndex = getCellIndex(row = row, col = col)
    onNode(hasTestTag(GAME_BOARD_TEST_TAG))
        .onChildren()
        .filter(hasClickAction())
        .filter(hasTestTag(getCellTestTag(cellIndex, player)))
        .onFirst()
        .performClick()
}

internal fun getCellTestTag(index: Int, player: Player? = null): String {
    return CELL_TEST_TAG + "_${index}_$player"
}

internal const val CELL_TEST_TAG = "cell"
internal const val GAME_BOARD_TEST_TAG = "game_board"
internal const val RESTART_GAME_BUTTON_TEXT = "Restart"
internal const val REPLAY_GAME_BUTTON_TEXT = "Again"
internal const val X_WINS_RESULT_STRING = "X Wins"
internal const val O_WINS_RESULT_STRING = "O Wins"
internal const val DRAW_RESULT_STRING = "Draw"