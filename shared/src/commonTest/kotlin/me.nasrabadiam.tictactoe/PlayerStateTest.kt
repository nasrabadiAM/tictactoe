package me.nasrabadiam.tictactoe

import androidx.lifecycle.SavedStateHandle
import me.nasrabadiam.tictactoe.game.model.Player
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class PlayerStateTest {

    @Test
    fun testSaveAndGetStateWhenInputIsDraw() {
        val input = Player.X
        val savedStateHandle = SavedStateHandle()
        input.saveState(savedStateHandle)

        val player = Player.getState(savedStateHandle)
        assertEquals(input, player)
        assertTrue(savedStateHandle.contains("player"))
    }

    @Test
    fun testSaveAndGetStateWhenInputIsEndWithWinner() {
        val input = Player.O
        val savedStateHandle = SavedStateHandle()
        input.saveState(savedStateHandle)

        val player = Player.getState(savedStateHandle)
        assertEquals(input, player)
        assertTrue(savedStateHandle.contains("player"))
    }

    @Test
    fun testGetStateWhenSavedStateIsEmpty() {
        val savedStateHandle = SavedStateHandle()

        val player = Player.getState(savedStateHandle)
        assertEquals(Player.X, player)
        assertFalse(savedStateHandle.contains("player"))
    }
}