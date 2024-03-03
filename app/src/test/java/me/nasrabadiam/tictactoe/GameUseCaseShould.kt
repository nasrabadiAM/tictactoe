package me.nasrabadiam.tictactoe

import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import me.nasrabadiam.tictactoe.game.GameUseCase
import me.nasrabadiam.tictactoe.game.Player
import me.nasrabadiam.tictactoe.game.utlis.getCellIndex
import org.junit.Test

class GameUseCaseShould {

    private val useCase = GameUseCase()

    @Test
    fun updateItemValueWhenClicked() = runTest {
        val index = 0
        useCase.onCellClicked(index)
        val clickedItem = useCase.cells.first()[index]
        assertEquals(Player.X, clickedItem.value)
    }

    @Test
    fun changePlayerTurnItemValueWhenClicked() = runTest {
        val index = 0
        useCase.onCellClicked(index)
        assertEquals(Player.O, useCase.currentPlayer)
    }

    @Test
    fun doNothingWhenACellWithValueClicked() = runTest {
        val index = 0
        repeat(2) {
            useCase.onCellClicked(index)
        }
        val clickedCell = useCase.cells.first()[index]
        assertEquals(Player.X, clickedCell.value)
        assertEquals(Player.O, useCase.currentPlayer)
    }

    @Test
    fun update8thItemValueOfCellsWhenClicked() = runTest {
        val index = 8
        useCase.onCellClicked(index)
        val clickedItem = useCase.cells.first()[index]
        assertEquals(Player.X, clickedItem.value)
    }

    @Test
    fun changePlayerTurnItemValueWhenClickedAndStarterIsO() = runTest {
        val index = 0
        val useCase = GameUseCase(starterPlayer = Player.O)
        useCase.onCellClicked(index)
        assertEquals(Player.X, useCase.currentPlayer)
    }

    @Test
    fun doNothingWhenCellsIsEmpty() = runTest {
        val index = 0
        val useCase = GameUseCase(boardSize = 0)
        useCase.onCellClicked(index)
        val cells = useCase.cells.first()
        assertTrue(cells.isEmpty())
        assertEquals(Player.X, useCase.currentPlayer)
    }

    /**
     * X Column tests
     */
    @Test
    fun emitGameResultWhenXFillsFirstColumn() = runTest {
        val xColumn = 0
        useCase.onCellClicked(getCellIndex(row = 0, col = xColumn)) // X
        useCase.onCellClicked(getCellIndex(row = 0, col = 1)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.onCellClicked(getCellIndex(row = 1, col = xColumn)) // X
        useCase.onCellClicked(getCellIndex(row = 1, col = 1)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.onCellClicked(getCellIndex(row = 2, col = xColumn)) // X
        assertEquals(Player.X, useCase.gameResult.value)
    }

    @Test
    fun emitGameResultWhenXFillsSecondColumn() = runTest {
        val xColumn = 1
        useCase.onCellClicked(getCellIndex(row = 0, col = xColumn)) // X
        useCase.onCellClicked(getCellIndex(row = 0, col = 0)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.onCellClicked(getCellIndex(row = 1, col = xColumn)) // X
        useCase.onCellClicked(getCellIndex(row = 1, col = 0)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.onCellClicked(getCellIndex(row = 2, col = xColumn)) // X
        assertEquals(Player.X, useCase.gameResult.value)
    }

    @Test
    fun emitGameResultWhenXFillsThirdColumn() = runTest {
        val xColumn = 2
        useCase.onCellClicked(getCellIndex(row = 0, col = xColumn)) // X
        useCase.onCellClicked(getCellIndex(row = 0, col = 0)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.onCellClicked(getCellIndex(row = 1, col = xColumn)) // X
        useCase.onCellClicked(getCellIndex(row = 1, col = 0)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.onCellClicked(getCellIndex(row = 2, col = xColumn)) // X
        assertEquals(Player.X, useCase.gameResult.value)
    }

    /**
     * X Row tests
     */
    @Test
    fun emitGameResultWhenXFillsFirstRow() = runTest {
        val xRow = 0
        useCase.onCellClicked(getCellIndex(row = xRow, col = 0)) // X
        useCase.onCellClicked(getCellIndex(row = 1, col = 0)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.onCellClicked(getCellIndex(row = xRow, col = 1)) // X
        useCase.onCellClicked(getCellIndex(row = 1, col = 1)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.onCellClicked(getCellIndex(row = xRow, col = 2)) // X
        assertEquals(Player.X, useCase.gameResult.value)
    }

    @Test
    fun emitGameResultWhenXFillsSecondRow() = runTest {
        val xRow = 1
        useCase.onCellClicked(getCellIndex(row = xRow, col = 0)) // X
        useCase.onCellClicked(getCellIndex(row = 2, col = 0)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.onCellClicked(getCellIndex(row = xRow, col = 1)) // X
        useCase.onCellClicked(getCellIndex(row = 0, col = 0)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.onCellClicked(getCellIndex(row = xRow, col = 2)) // X
        assertEquals(Player.X, useCase.gameResult.value)
    }

    @Test
    fun emitGameResultWhenXFillsThirdRow() = runTest {
        val xRow = 2
        useCase.onCellClicked(getCellIndex(row = xRow, col = 0)) // X
        useCase.onCellClicked(getCellIndex(row = 1, col = 0)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.onCellClicked(getCellIndex(row = xRow, col = 1)) // X
        useCase.onCellClicked(getCellIndex(row = 1, col = 1)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.onCellClicked(getCellIndex(row = xRow, col = 2)) // X
        assertEquals(Player.X, useCase.gameResult.value)
    }


    /**
     * X Cross tests
     */
    @Test
    fun emitGameResultWhenXFillsLeftToRightCross() = runTest {
        useCase.onCellClicked(getCellIndex(row = 0, col = 0)) // X
        useCase.onCellClicked(getCellIndex(row = 0, col = 2)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.onCellClicked(getCellIndex(row = 1, col = 1)) // X
        useCase.onCellClicked(getCellIndex(row = 1, col = 2)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.onCellClicked(getCellIndex(row = 2, col = 2)) // X
        assertEquals(Player.X, useCase.gameResult.value)
    }

    @Test
    fun emitGameResultWhenXFillsRightToLeftCross() = runTest {
        useCase.onCellClicked(getCellIndex(row = 0, col = 2)) // X
        useCase.onCellClicked(getCellIndex(row = 1, col = 2)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.onCellClicked(getCellIndex(row = 1, col = 1)) // X
        useCase.onCellClicked(getCellIndex(row = 1, col = 0)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.onCellClicked(getCellIndex(row = 2, col = 0)) // X
        assertEquals(Player.X, useCase.gameResult.value)
    }

    /**
     * O Column tests
     */
    @Test
    fun emitGameResultWhenOFillsFirstColumn() = runTest {
        val oColumn = 0
        useCase.onCellClicked(getCellIndex(row = 2, col = 1)) // X
        useCase.onCellClicked(getCellIndex(row = 0, col = oColumn)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.onCellClicked(getCellIndex(row = 2, col = 2)) // X
        useCase.onCellClicked(getCellIndex(row = 1, col = oColumn)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.onCellClicked(getCellIndex(row = 1, col = 1)) // X
        useCase.onCellClicked(getCellIndex(row = 2, col = oColumn)) // O
        assertEquals(Player.O, useCase.gameResult.value)
    }

    @Test
    fun emitGameResultWhenOFillsSecondColumn() = runTest {
        val oColumn = 1
        useCase.onCellClicked(getCellIndex(row = 0, col = 0)) // X
        useCase.onCellClicked(getCellIndex(row = 0, col = oColumn)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.onCellClicked(getCellIndex(row = 1, col = 0)) // X
        useCase.onCellClicked(getCellIndex(row = 1, col = oColumn)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.onCellClicked(getCellIndex(row = 2, col = 2)) // X
        useCase.onCellClicked(getCellIndex(row = 2, col = oColumn)) // X
        assertEquals(Player.O, useCase.gameResult.value)
    }

    @Test
    fun emitGameResultWhenOFillsThirdColumn() = runTest {
        val oColumn = 2
        useCase.onCellClicked(getCellIndex(row = 0, col = 0)) // X
        useCase.onCellClicked(getCellIndex(row = 0, col = oColumn)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.onCellClicked(getCellIndex(row = 1, col = 0)) // X
        useCase.onCellClicked(getCellIndex(row = 1, col = oColumn)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.onCellClicked(getCellIndex(row = 2, col = 1)) // X
        useCase.onCellClicked(getCellIndex(row = 2, col = oColumn)) // X
        assertEquals(Player.O, useCase.gameResult.value)
    }

    /**
     * O Row tests
     */
    @Test
    fun emitGameResultWhenOFillsFirstRow() = runTest {
        val oRow = 0
        useCase.onCellClicked(getCellIndex(row = 1, col = 0)) // X
        useCase.onCellClicked(getCellIndex(row = oRow, col = 0)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.onCellClicked(getCellIndex(row = 1, col = 1)) // X
        useCase.onCellClicked(getCellIndex(row = oRow, col = 1)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.onCellClicked(getCellIndex(row = 2, col = 2)) // X
        useCase.onCellClicked(getCellIndex(row = oRow, col = 2)) // O
        assertEquals(Player.O, useCase.gameResult.value)
    }

    @Test
    fun emitGameResultWhenOFillsSecondRow() = runTest {
        val oRow = 1
        useCase.onCellClicked(getCellIndex(row = 2, col = 0)) // X
        useCase.onCellClicked(getCellIndex(row = oRow, col = 0)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.onCellClicked(getCellIndex(row = 2, col = 1)) // X
        useCase.onCellClicked(getCellIndex(row = oRow, col = 1)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.onCellClicked(getCellIndex(row = 0, col = 0)) // X
        useCase.onCellClicked(getCellIndex(row = oRow, col = 2)) // O
        assertEquals(Player.O, useCase.gameResult.value)
    }

    @Test
    fun emitGameResultWhenOFillsThirdRow() = runTest {
        val oRow = 2
        useCase.onCellClicked(getCellIndex(row = 1, col = 0)) // X
        useCase.onCellClicked(getCellIndex(row = oRow, col = 0)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.onCellClicked(getCellIndex(row = 1, col = 1)) // X
        useCase.onCellClicked(getCellIndex(row = oRow, col = 1)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.onCellClicked(getCellIndex(row = 0, col = 2)) // X
        useCase.onCellClicked(getCellIndex(row = oRow, col = 2)) // O
        assertEquals(Player.O, useCase.gameResult.value)
    }


    /**
     * O Cross tests
     */
    @Test
    fun emitGameResultWhenOFillsLeftToRightCross() = runTest {
        useCase.onCellClicked(getCellIndex(row = 0, col = 2)) // X
        useCase.onCellClicked(getCellIndex(row = 0, col = 0)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.onCellClicked(getCellIndex(row = 1, col = 2)) // X
        useCase.onCellClicked(getCellIndex(row = 1, col = 1)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.onCellClicked(getCellIndex(row = 2, col = 0)) // X
        useCase.onCellClicked(getCellIndex(row = 2, col = 2)) // O
        assertEquals(Player.O, useCase.gameResult.value)
    }

    @Test
    fun emitGameResultWhenOFillsRightToLeftCross() = runTest {
        useCase.onCellClicked(getCellIndex(row = 1, col = 2)) // X
        useCase.onCellClicked(getCellIndex(row = 0, col = 2)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.onCellClicked(getCellIndex(row = 1, col = 0)) // X
        useCase.onCellClicked(getCellIndex(row = 1, col = 1)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.onCellClicked(getCellIndex(row = 2, col = 1)) // X
        useCase.onCellClicked(getCellIndex(row = 2, col = 0)) // O
        assertEquals(Player.O, useCase.gameResult.value)
    }
}