package me.nasrabadiam.tictactoe

import androidx.lifecycle.SavedStateHandle
import me.nasrabadiam.tictactoe.game.model.Player.O
import me.nasrabadiam.tictactoe.game.model.Player.X
import me.nasrabadiam.tictactoe.game.model.utlis.getCellsListState
import me.nasrabadiam.tictactoe.game.model.utlis.listOfEmptyCells
import me.nasrabadiam.tictactoe.game.model.utlis.saveState
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class CellStateTest {
    @Test
    fun testSaveAndGetStateWhenInputIsListOfEmptyCells() {
        val input = listOfEmptyCells()
        val savedStateHandle = SavedStateHandle()

        input.saveState(savedStateHandle)

        val cells = getCellsListState(savedStateHandle)
        assertEquals(input, cells)
        assertTrue(savedStateHandle.contains("cells_key"))
    }

    @Test
    fun testSaveAndGetStateWhenInputIsListOfCells() {
        val input = listOfEmptyCells().toMutableList().apply {
            this[0] = this[0].copy(value = X)
            this[2] = this[2].copy(value = O)
            this[8] = this[8].copy(value = X)
        }
        val savedStateHandle = SavedStateHandle()

        input.saveState(savedStateHandle)

        val cells = getCellsListState(savedStateHandle)
        assertEquals(input, cells)
        assertTrue(savedStateHandle.contains("cells_key"))
    }

    @Test
    fun testGetStateWhenSavedInstanceIsEmpty() {
        val savedStateHandle = SavedStateHandle()

        val cells = getCellsListState(savedStateHandle)
        assertEquals(listOfEmptyCells(), cells)
        assertFalse(savedStateHandle.contains("cells_key"))
    }
}