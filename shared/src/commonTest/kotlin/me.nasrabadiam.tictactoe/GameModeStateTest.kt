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
        val input = GameMode.PLAYER_VS_PLAYER
        val savedStateHandle = SavedStateHandle()
        input.saveState(savedStateHandle)

        val gameMode = GameMode.getState(savedStateHandle, GameMode.PLAYER_VS_AI)
        assertEquals(input, gameMode)
        assertTrue(savedStateHandle.contains("game_mode"))
    }

    @Test
    fun testSaveAndGetStateWhenInputIsPlayerVsAI() {
        val input = GameMode.PLAYER_VS_AI
        val savedStateHandle = SavedStateHandle()
        input.saveState(savedStateHandle)

        val gameMode = GameMode.getState(savedStateHandle, GameMode.PLAYER_VS_PLAYER)
        assertEquals(input, gameMode)
        assertTrue(savedStateHandle.contains("game_mode"))
    }

    @Test
    fun testGetStateWhenSavedStateIsEmptyReturnsDefault() {
        val savedStateHandle = SavedStateHandle()
        val defaultMode = GameMode.PLAYER_VS_PLAYER

        val gameMode = GameMode.getState(savedStateHandle, defaultMode)
        assertEquals(defaultMode, gameMode)
        assertFalse(savedStateHandle.contains("game_mode"))
    }

    @Test
    fun testGetStateWhenSavedStateIsEmptyReturnsPlayerVsAIDefault() {
        val savedStateHandle = SavedStateHandle()
        val defaultMode = GameMode.PLAYER_VS_AI

        val gameMode = GameMode.getState(savedStateHandle, defaultMode)
        assertEquals(defaultMode, gameMode)
        assertFalse(savedStateHandle.contains("game_mode"))
    }
}