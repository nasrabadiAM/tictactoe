package me.nasrabadiam.tictactoe

import junit.framework.TestCase.assertEquals
import me.nasrabadiam.tictactoe.game.DEFAULT_BOARD_SIZE
import me.nasrabadiam.tictactoe.game.utlis.getBoardSize
import me.nasrabadiam.tictactoe.game.utlis.listOfEmptyCells
import org.junit.Test

class UtilsTest {

    @Test
    fun getBoardSizeFromBoardList() {
        val cells = listOfEmptyCells(DEFAULT_BOARD_SIZE)
        val boardSize = cells.getBoardSize()
        assertEquals(3, boardSize)
    }

    @Test
    fun getBoardSizeFromBoardListWhenListIs16() {
        val cells = listOfEmptyCells(16)
        val boardSize = cells.getBoardSize()
        assertEquals(4, boardSize)
    }
}