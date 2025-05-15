package me.nasrabadiam.tictactoe

import androidx.lifecycle.SavedStateHandle
import me.nasrabadiam.tictactoe.game.ui.ScoresState
import me.nasrabadiam.tictactoe.game.ui.saveDrawCount
import me.nasrabadiam.tictactoe.game.ui.saveOScore
import me.nasrabadiam.tictactoe.game.ui.saveXScore
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ScoresStateTest {

    @Test
    fun testSaveAndGetState() {
        val input = ScoresState(xScore = 1, oScore = 2, drawCount = 3)
        val savedStateHandle = SavedStateHandle()
        input.oScore.saveOScore(savedStateHandle)
        input.xScore.saveXScore(savedStateHandle)
        input.drawCount.saveDrawCount(savedStateHandle)

        val scoreState = ScoresState.getState(savedStateHandle)

        assertEquals(input, scoreState)
        assertTrue(savedStateHandle.contains("draw_count"))
        assertTrue(savedStateHandle.contains("x_score"))
        assertTrue(savedStateHandle.contains("o_score"))
    }

    @Test
    fun testGetStateWhenSavedInstanceIsEmpty() {
        val savedStateHandle = SavedStateHandle()

        val scoreState = ScoresState.getState(savedStateHandle)

        assertEquals(ScoresState(), scoreState)
        assertFalse(savedStateHandle.contains("draw_count"))
        assertFalse(savedStateHandle.contains("x_score"))
        assertFalse(savedStateHandle.contains("o_score"))
    }
}