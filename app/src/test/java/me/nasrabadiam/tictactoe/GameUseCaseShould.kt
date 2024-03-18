package me.nasrabadiam.tictactoe

import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import me.nasrabadiam.tictactoe.game.GameUseCase
import me.nasrabadiam.tictactoe.game.model.GameResult.Draw
import me.nasrabadiam.tictactoe.game.model.GameResult.EndWithWinner
import me.nasrabadiam.tictactoe.game.model.Player
import me.nasrabadiam.tictactoe.game.model.utlis.getCellIndex
import org.junit.Test

class GameUseCaseShould {

    private val useCase = GameUseCase()

    @Test
    fun updateItemValueWhenClicked() = runTest {
        val index = 0
        useCase.clickOnCell(index)
        val clickedItem = useCase.cells.first()[index]
        assertEquals(Player.X, clickedItem.value)
    }

    @Test
    fun changePlayerTurnItemValueWhenClicked() = runTest {
        val index = 0
        useCase.clickOnCell(index)
        assertEquals(Player.O, useCase.currentPlayer)
    }

    @Test
    fun doNothingWhenACellWithValueClicked() = runTest {
        val index = 0
        repeat(2) {
            useCase.clickOnCell(index)
        }
        val clickedCell = useCase.cells.first()[index]
        assertEquals(Player.X, clickedCell.value)
        assertEquals(Player.O, useCase.currentPlayer)
    }

    @Test
    fun update8thItemValueOfCellsWhenClicked() = runTest {
        val index = 8
        useCase.clickOnCell(index)
        val clickedItem = useCase.cells.first()[index]
        assertEquals(Player.X, clickedItem.value)
    }

    @Test
    fun changePlayerTurnItemValueWhenClickedAndStarterIsO() = runTest {
        val index = 0
        val useCase = GameUseCase(starterPlayer = Player.O)
        useCase.clickOnCell(index)
        assertEquals(Player.X, useCase.currentPlayer)
    }

    @Test
    fun doNothingWhenCellsIsEmpty() = runTest {
        val index = 0
        val useCase = GameUseCase(boardSize = 0)
        useCase.clickOnCell(index)
        val cells = useCase.cells.first()
        assertTrue(
            "Cells must be empty, but it's not.",
            cells.isEmpty()
        )
        assertEquals(Player.X, useCase.currentPlayer)
    }

    /**
     * X Column tests
     */
    @Test
    fun emitGameResultWhenXFillsFirstColumn() = runTest {
        val xColumn = 0
        useCase.clickOnCell(getCellIndex(row = 0, col = xColumn)) // X
        useCase.clickOnCell(getCellIndex(row = 0, col = 1)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.clickOnCell(getCellIndex(row = 1, col = xColumn)) // X
        useCase.clickOnCell(getCellIndex(row = 1, col = 1)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.clickOnCell(getCellIndex(row = 2, col = xColumn)) // X
        assertEquals(EndWithWinner(Player.X), useCase.gameResult.value)
    }

    @Test
    fun emitGameResultWhenXFillsSecondColumn() = runTest {
        val xColumn = 1
        useCase.clickOnCell(getCellIndex(row = 0, col = xColumn)) // X
        useCase.clickOnCell(getCellIndex(row = 0, col = 0)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.clickOnCell(getCellIndex(row = 1, col = xColumn)) // X
        useCase.clickOnCell(getCellIndex(row = 1, col = 0)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.clickOnCell(getCellIndex(row = 2, col = xColumn)) // X
        assertEquals(EndWithWinner(Player.X), useCase.gameResult.value)
    }

    @Test
    fun emitGameResultWhenXFillsThirdColumn() = runTest {
        val xColumn = 2
        useCase.clickOnCell(getCellIndex(row = 0, col = xColumn)) // X
        useCase.clickOnCell(getCellIndex(row = 0, col = 0)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.clickOnCell(getCellIndex(row = 1, col = xColumn)) // X
        useCase.clickOnCell(getCellIndex(row = 1, col = 0)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.clickOnCell(getCellIndex(row = 2, col = xColumn)) // X
        assertEquals(EndWithWinner(Player.X), useCase.gameResult.value)
    }

    /**
     * X Row tests
     */
    @Test
    fun emitGameResultWhenXFillsFirstRow() = runTest {
        val xRow = 0
        useCase.clickOnCell(getCellIndex(row = xRow, col = 0)) // X
        useCase.clickOnCell(getCellIndex(row = 1, col = 0)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.clickOnCell(getCellIndex(row = xRow, col = 1)) // X
        useCase.clickOnCell(getCellIndex(row = 1, col = 1)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.clickOnCell(getCellIndex(row = xRow, col = 2)) // X
        assertEquals(EndWithWinner(Player.X), useCase.gameResult.value)
    }

    @Test
    fun emitGameResultWhenXFillsSecondRow() = runTest {
        val xRow = 1
        useCase.clickOnCell(getCellIndex(row = xRow, col = 0)) // X
        useCase.clickOnCell(getCellIndex(row = 2, col = 0)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.clickOnCell(getCellIndex(row = xRow, col = 1)) // X
        useCase.clickOnCell(getCellIndex(row = 0, col = 0)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.clickOnCell(getCellIndex(row = xRow, col = 2)) // X
        assertEquals(EndWithWinner(Player.X), useCase.gameResult.value)
    }

    @Test
    fun emitGameResultWhenXFillsThirdRow() = runTest {
        val xRow = 2
        useCase.clickOnCell(getCellIndex(row = xRow, col = 0)) // X
        useCase.clickOnCell(getCellIndex(row = 1, col = 0)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.clickOnCell(getCellIndex(row = xRow, col = 1)) // X
        useCase.clickOnCell(getCellIndex(row = 1, col = 1)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.clickOnCell(getCellIndex(row = xRow, col = 2)) // X
        assertEquals(EndWithWinner(Player.X), useCase.gameResult.value)
    }

    /**
     * X Cross tests
     */
    @Test
    fun emitGameResultWhenXFillsLeftToRightCross() = runTest {
        useCase.clickOnCell(getCellIndex(row = 0, col = 0)) // X
        useCase.clickOnCell(getCellIndex(row = 0, col = 2)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.clickOnCell(getCellIndex(row = 1, col = 1)) // X
        useCase.clickOnCell(getCellIndex(row = 1, col = 2)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.clickOnCell(getCellIndex(row = 2, col = 2)) // X
        assertEquals(EndWithWinner(Player.X), useCase.gameResult.value)
    }

    @Test
    fun emitGameResultWhenXFillsRightToLeftCross() = runTest {
        useCase.clickOnCell(getCellIndex(row = 0, col = 2)) // X
        useCase.clickOnCell(getCellIndex(row = 1, col = 2)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.clickOnCell(getCellIndex(row = 1, col = 1)) // X
        useCase.clickOnCell(getCellIndex(row = 1, col = 0)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.clickOnCell(getCellIndex(row = 2, col = 0)) // X
        assertEquals(EndWithWinner(Player.X), useCase.gameResult.value)
    }

    /**
     * O Column tests
     */
    @Test
    fun emitGameResultWhenOFillsFirstColumn() = runTest {
        val oColumn = 0
        useCase.clickOnCell(getCellIndex(row = 2, col = 1)) // X
        useCase.clickOnCell(getCellIndex(row = 0, col = oColumn)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.clickOnCell(getCellIndex(row = 2, col = 2)) // X
        useCase.clickOnCell(getCellIndex(row = 1, col = oColumn)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.clickOnCell(getCellIndex(row = 1, col = 1)) // X
        useCase.clickOnCell(getCellIndex(row = 2, col = oColumn)) // O
        assertEquals(EndWithWinner(Player.O), useCase.gameResult.value)
    }

    @Test
    fun emitGameResultWhenOFillsSecondColumn() = runTest {
        val oColumn = 1
        useCase.clickOnCell(getCellIndex(row = 0, col = 0)) // X
        useCase.clickOnCell(getCellIndex(row = 0, col = oColumn)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.clickOnCell(getCellIndex(row = 1, col = 0)) // X
        useCase.clickOnCell(getCellIndex(row = 1, col = oColumn)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.clickOnCell(getCellIndex(row = 2, col = 2)) // X
        useCase.clickOnCell(getCellIndex(row = 2, col = oColumn)) // X
        assertEquals(EndWithWinner(Player.O), useCase.gameResult.value)
    }

    @Test
    fun emitGameResultWhenOFillsThirdColumn() = runTest {
        val oColumn = 2
        useCase.clickOnCell(getCellIndex(row = 0, col = 0)) // X
        useCase.clickOnCell(getCellIndex(row = 0, col = oColumn)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.clickOnCell(getCellIndex(row = 1, col = 0)) // X
        useCase.clickOnCell(getCellIndex(row = 1, col = oColumn)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.clickOnCell(getCellIndex(row = 2, col = 1)) // X
        useCase.clickOnCell(getCellIndex(row = 2, col = oColumn)) // X
        assertEquals(EndWithWinner(Player.O), useCase.gameResult.value)
    }

    /**
     * O Row tests
     */
    @Test
    fun emitGameResultWhenOFillsFirstRow() = runTest {
        val oRow = 0
        useCase.clickOnCell(getCellIndex(row = 1, col = 0)) // X
        useCase.clickOnCell(getCellIndex(row = oRow, col = 0)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.clickOnCell(getCellIndex(row = 1, col = 1)) // X
        useCase.clickOnCell(getCellIndex(row = oRow, col = 1)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.clickOnCell(getCellIndex(row = 2, col = 2)) // X
        useCase.clickOnCell(getCellIndex(row = oRow, col = 2)) // O
        assertEquals(EndWithWinner(Player.O), useCase.gameResult.value)
    }

    @Test
    fun emitGameResultWhenOFillsSecondRow() = runTest {
        val oRow = 1
        useCase.clickOnCell(getCellIndex(row = 2, col = 0)) // X
        useCase.clickOnCell(getCellIndex(row = oRow, col = 0)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.clickOnCell(getCellIndex(row = 2, col = 1)) // X
        useCase.clickOnCell(getCellIndex(row = oRow, col = 1)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.clickOnCell(getCellIndex(row = 0, col = 0)) // X
        useCase.clickOnCell(getCellIndex(row = oRow, col = 2)) // O
        assertEquals(EndWithWinner(Player.O), useCase.gameResult.value)
    }

    @Test
    fun emitGameResultWhenOFillsThirdRow() = runTest {
        val oRow = 2
        useCase.clickOnCell(getCellIndex(row = 1, col = 0)) // X
        useCase.clickOnCell(getCellIndex(row = oRow, col = 0)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.clickOnCell(getCellIndex(row = 1, col = 1)) // X
        useCase.clickOnCell(getCellIndex(row = oRow, col = 1)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.clickOnCell(getCellIndex(row = 0, col = 2)) // X
        useCase.clickOnCell(getCellIndex(row = oRow, col = 2)) // O
        assertEquals(EndWithWinner(Player.O), useCase.gameResult.value)
    }

    /**
     * O Cross tests
     */
    @Test
    fun emitGameResultWhenOFillsLeftToRightCross() = runTest {
        useCase.clickOnCell(getCellIndex(row = 0, col = 2)) // X
        useCase.clickOnCell(getCellIndex(row = 0, col = 0)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.clickOnCell(getCellIndex(row = 1, col = 2)) // X
        useCase.clickOnCell(getCellIndex(row = 1, col = 1)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.clickOnCell(getCellIndex(row = 2, col = 0)) // X
        useCase.clickOnCell(getCellIndex(row = 2, col = 2)) // O
        assertEquals(EndWithWinner(Player.O), useCase.gameResult.value)
    }

    @Test
    fun emitGameResultWhenOFillsRightToLeftCross() = runTest {
        useCase.clickOnCell(getCellIndex(row = 1, col = 2)) // X
        useCase.clickOnCell(getCellIndex(row = 0, col = 2)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.clickOnCell(getCellIndex(row = 1, col = 0)) // X
        useCase.clickOnCell(getCellIndex(row = 1, col = 1)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.clickOnCell(getCellIndex(row = 2, col = 1)) // X
        useCase.clickOnCell(getCellIndex(row = 2, col = 0)) // O
        assertEquals(EndWithWinner(Player.O), useCase.gameResult.value)
    }

    @Test
    fun emitDrawGameResultWhenNoOneWins() = runTest {
        useCase.clickOnCell(getCellIndex(col = 0, row = 0)) // X
        useCase.clickOnCell(getCellIndex(col = 2, row = 0)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.clickOnCell(getCellIndex(col = 1, row = 1)) // X
        useCase.clickOnCell(getCellIndex(col = 0, row = 1)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.clickOnCell(getCellIndex(col = 0, row = 2)) // X
        useCase.clickOnCell(getCellIndex(col = 2, row = 2)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.clickOnCell(getCellIndex(col = 2, row = 1)) // X
        useCase.clickOnCell(getCellIndex(col = 1, row = 0)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.clickOnCell(getCellIndex(col = 1, row = 2)) // X
        assertEquals(Draw, useCase.gameResult.value)
    }

    @Test
    fun resetGameCellsWhenClickedOnRestartButton() = runTest {
        useCase.clickOnCell(getCellIndex(col = 1, row = 2)) // X
        useCase.clickOnCell(getCellIndex(col = 0, row = 0)) // O

        useCase.restartGame()

        val cells = useCase.cells.first()
        assertTrue(
            "All cells should be empty after restart but they aren't," +
                " non empty cells=${cells.filter { it.value != null }}",
            cells.all { it.value == null }
        )
    }

    @Test
    fun resetGameResultWhenClickedOnRestartButton() = runTest {
        useCase.clickOnCell(getCellIndex(col = 0, row = 0)) // X
        useCase.clickOnCell(getCellIndex(col = 2, row = 0)) // O

        useCase.clickOnCell(getCellIndex(col = 1, row = 1)) // X
        useCase.clickOnCell(getCellIndex(col = 0, row = 1)) // O

        useCase.clickOnCell(getCellIndex(col = 0, row = 2)) // X
        useCase.clickOnCell(getCellIndex(col = 2, row = 2)) // O

        useCase.clickOnCell(getCellIndex(col = 2, row = 1)) // X
        useCase.clickOnCell(getCellIndex(col = 1, row = 0)) // O

        useCase.clickOnCell(getCellIndex(col = 1, row = 2)) // X

        useCase.restartGame()

        val gameResult = useCase.gameResult.value
        assertTrue(
            "Game Result should by empty when game has been reset," +
                " but it is -> gameResult=$gameResult",
            gameResult == null
        )
    }

    @Test
    fun resetGameCurrentPlayerToStarterPlayerWhenClickedOnRestartButton() = runTest {
        // Starter player here is X
        useCase.clickOnCell(getCellIndex(col = 0, row = 0)) // X
        // Current player here is O

        useCase.restartGame()

        val currentPlayer = useCase.currentPlayer
        assertTrue(
            "Current Player must be restart to starter player, " +
                "when we restart the game but it is -> currentPlayer=$currentPlayer",
            currentPlayer == Player.X
        )
    }

    @Test
    fun emitXScoreWhenItWins() {
        val xColumn = 1
        useCase.clickOnCell(getCellIndex(row = 0, col = xColumn)) // X
        useCase.clickOnCell(getCellIndex(row = 0, col = 0)) // O

        useCase.clickOnCell(getCellIndex(row = 1, col = xColumn)) // X
        useCase.clickOnCell(getCellIndex(row = 1, col = 0)) // O

        useCase.clickOnCell(getCellIndex(row = 2, col = xColumn)) // X wins
        assertEquals(1, useCase.xScore.value)
    }

    @Test
    fun emitOScoreWhenItWins() {
        useCase.clickOnCell(getCellIndex(row = 0, col = 2)) // X
        useCase.clickOnCell(getCellIndex(row = 0, col = 0)) // O

        useCase.clickOnCell(getCellIndex(row = 1, col = 2)) // X
        useCase.clickOnCell(getCellIndex(row = 1, col = 1)) // O

        useCase.clickOnCell(getCellIndex(row = 2, col = 0)) // X
        useCase.clickOnCell(getCellIndex(row = 2, col = 2)) // O wins

        assertEquals(1, useCase.oScore.value)
    }

    @Test
    fun emitDrawCountWhenItDraws() = with(useCase) {

        clickOnCell(getCellIndex(col = 0, row = 0)) // X
        clickOnCell(getCellIndex(col = 2, row = 0)) // O

        clickOnCell(getCellIndex(col = 1, row = 1)) // X
        clickOnCell(getCellIndex(col = 0, row = 1)) // O

        clickOnCell(getCellIndex(col = 0, row = 2)) // X
        clickOnCell(getCellIndex(col = 2, row = 2)) // O

        clickOnCell(getCellIndex(col = 2, row = 1)) // X
        clickOnCell(getCellIndex(col = 1, row = 0)) // O

        clickOnCell(getCellIndex(col = 1, row = 2)) // X -> Draw

        assertEquals(1, useCase.drawCount.value)
    }
}