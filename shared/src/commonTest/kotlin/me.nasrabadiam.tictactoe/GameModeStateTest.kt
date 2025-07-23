package me.nasrabadiam.tictactoe

import androidx.lifecycle.SavedStateHandle
import me.nasrabadiam.tictactoe.game.model.GameMode
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class GameModeStateTest {

    @Test
    fun testSaveAndGetStateWhenInputIsPlayerVsPlayer() {
        val input = GameMode.PlayWithFriend
        val savedStateHandle = SavedStateHandle()
        input.saveState(savedStateHandle)

        val gameMode = GameMode.getState(savedStateHandle, GameMode.PlayWithAI())
        assertEquals(input, gameMode)
        assertTrue(savedStateHandle.contains("game_mode"))
    }

    @Test
    fun testSaveAndGetStateWhenInputIsPlayerVsAI() {
        val input = GameMode.PlayWithAI()
        val savedStateHandle = SavedStateHandle()
        input.saveState(savedStateHandle)

        val gameMode = GameMode.getState(savedStateHandle, GameMode.PlayWithFriend)
        assertEquals(input, gameMode)
        assertTrue(savedStateHandle.contains("game_mode"))
    }

    @Test
    fun testGetStateWhenSavedStateIsEmptyReturnsDefault() {
        val savedStateHandle = SavedStateHandle()
        val defaultMode = GameMode.PlayWithFriend

        val gameMode = GameMode.getState(savedStateHandle, defaultMode)
        assertEquals(defaultMode, gameMode)
        assertFalse(savedStateHandle.contains("game_mode"))
    }

    @Test
    fun testGetStateWhenSavedStateIsEmptyReturnsPlayerVsAIDefault() {
        val savedStateHandle = SavedStateHandle()
        val defaultMode = GameMode.PlayWithAI()

        val gameMode = GameMode.getState(savedStateHandle, defaultMode)
        assertEquals(defaultMode, gameMode)
        assertFalse(savedStateHandle.contains("game_mode"))
    }
}