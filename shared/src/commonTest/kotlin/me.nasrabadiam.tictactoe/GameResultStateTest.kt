package me.nasrabadiam.tictactoe

import androidx.lifecycle.SavedStateHandle
import me.nasrabadiam.tictactoe.game.GameUseCase.Orientation
import me.nasrabadiam.tictactoe.game.model.GameResult
import me.nasrabadiam.tictactoe.game.model.Player
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue

class GameResultStateTest {

    @Test
    fun testSaveAndGetStateWhenInputIsDraw() {
        val input = GameResult.Draw
        val savedStateHandle = SavedStateHandle()
        input.saveState(savedStateHandle)

        val gameResult = GameResult.getState(savedStateHandle)
        assertEquals(input, gameResult)
        assertTrue(savedStateHandle.contains("game_result"))
    }

    @Test
    fun testSaveAndGetStateWhenInputIsEndWithWinner() {
        val input = GameResult.EndWithWinner(
            Player.X,
            Orientation.ROW,
            1,
        )
        val savedStateHandle = SavedStateHandle()
        input.saveState(savedStateHandle)

        val result = GameResult.getState(savedStateHandle)
        assertEquals(input, result)
        assertTrue(savedStateHandle.contains("game_result"))
    }

    @Test
    fun testGetStateWhenSavedStateHandleIsEmpty() {
        val savedStateHandle = SavedStateHandle()

        val result = GameResult.getState(savedStateHandle)
        assertNull(result)
        assertFalse(savedStateHandle.contains("game_result"))
    }
}