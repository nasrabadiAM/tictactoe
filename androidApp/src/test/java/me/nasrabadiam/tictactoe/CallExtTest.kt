package me.nasrabadiam.tictactoe

import junit.framework.TestCase.assertEquals
import me.nasrabadiam.tictactoe.game.model.utlis.getColumnIndex
import me.nasrabadiam.tictactoe.game.model.utlis.getRowIndex
import me.nasrabadiam.tictactoe.game.model.utlis.listOfEmptyCells
import org.junit.Test

class CallExtTest {

    private val cells = listOfEmptyCells()

    @Test
    fun getCellRowFromIndex0() {
        val rowIndex = cells.getRowIndex(0)
        assertEquals(0, rowIndex)
    }

    @Test
    fun getCellRowFromIndex4() {
        val rowIndex = cells.getRowIndex(4)
        assertEquals(1, rowIndex)
    }

    @Test
    fun getCellRowFromIndex8() {
        val rowIndex = cells.getRowIndex(8)
        assertEquals(2, rowIndex)
    }

    @Test
    fun getCellColumnFromIndex2() {
        val colIndex = cells.getColumnIndex(2)
        assertEquals(2, colIndex)
    }

    @Test
    fun getCellColumnFromIndex4() {
        val colIndex = cells.getColumnIndex(4)
        assertEquals(1, colIndex)
    }

    @Test
    fun getCellColumnFromIndex6() {
        val colIndex = cells.getColumnIndex(6)
        assertEquals(0, colIndex)
    }
}