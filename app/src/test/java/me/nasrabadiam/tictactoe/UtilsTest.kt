package me.nasrabadiam.tictactoe

import junit.framework.TestCase.assertEquals
import me.nasrabadiam.tictactoe.game.model.DEFAULT_BOARD_CELL_COUNT
import me.nasrabadiam.tictactoe.game.model.utlis.getBoardSize
import me.nasrabadiam.tictactoe.game.model.utlis.getCellIndex
import me.nasrabadiam.tictactoe.game.model.utlis.listOfEmptyCells
import org.junit.Test

class UtilsTest {

    @Test
    fun getBoardSizeFromBoardList() {
        val cells = listOfEmptyCells(DEFAULT_BOARD_CELL_COUNT)
        val boardSize = cells.getBoardSize()
        assertEquals(3, boardSize)
    }

    @Test
    fun getBoardSizeFromBoardListWhenListIs16() {
        val cells = listOfEmptyCells(16)
        val boardSize = cells.getBoardSize()
        assertEquals(4, boardSize)
    }

    @Test
    fun getCellFromColumn0AndRow0() {
        val cells = listOfEmptyCells(DEFAULT_BOARD_CELL_COUNT)
        val index = cells.getCellIndex(col = 0, row = 0)
        assertEquals(0, index)
    }

    @Test
    fun getCellFromColumn1AndRow0() {
        val cells = listOfEmptyCells(DEFAULT_BOARD_CELL_COUNT)
        val index = cells.getCellIndex(col = 1, row = 0)
        assertEquals(1, index)
    }

    @Test
    fun getCellFromColumn2AndRow0() {
        val cells = listOfEmptyCells(DEFAULT_BOARD_CELL_COUNT)
        val index = cells.getCellIndex(col = 2, row = 0)
        assertEquals(2, index)
    }

    @Test
    fun getCellFromColumn3AndRow3() {
        val cells = listOfEmptyCells(DEFAULT_BOARD_CELL_COUNT)
        val index = cells.getCellIndex(col = 2, row = 2)
        assertEquals(8, index)
    }

    @Test
    fun getCellFromColumn1AndRow1() {
        val cells = listOfEmptyCells(DEFAULT_BOARD_CELL_COUNT)
        val index = cells.getCellIndex(col = 1, row = 1)
        assertEquals(4, index)
    }
}