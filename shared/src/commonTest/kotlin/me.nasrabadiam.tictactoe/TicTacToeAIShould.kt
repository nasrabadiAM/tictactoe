package me.nasrabadiam.tictactoe

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import me.nasrabadiam.tictactoe.game.ai.TicTacToeAI
import me.nasrabadiam.tictactoe.game.model.AI_MOVE_DELAY_IN_MILLIS
import me.nasrabadiam.tictactoe.game.model.Player.O
import me.nasrabadiam.tictactoe.game.model.Player.X
import me.nasrabadiam.tictactoe.game.model.utlis.listOfEmptyCells
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class TicTacToeAIShould {
    
    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()
    private val ai = TicTacToeAI(defaultDispatcher = testDispatcher)

    @Test
    fun blockHumanWinInRow() = runTest {
        // X has two in top row, AI should block
        val cells = listOfEmptyCells().toMutableList().apply {
            this[0] = this[0].copy(value = X) // X at (0,0)
            this[1] = this[1].copy(value = X) // X at (0,1)
            // Position 2 (0,2) should be blocked by AI
        }

        var aiMove = -1
        ai.scheduleAIMove(cells) { move ->
            aiMove = move
        }

        testDispatcher.scheduler.advanceTimeBy(AI_MOVE_DELAY_IN_MILLIS + 1)

        assertEquals(2, aiMove, "AI should block human win at position 2")
    }

    @Test
    fun blockHumanWinInColumn() = runTest {
        // X has two in first column, AI should block
        val cells = listOfEmptyCells().toMutableList().apply {
            this[0] = this[0].copy(value = X) // X at (0,0)
            this[3] = this[3].copy(value = X) // X at (1,0)
            // Position 6 (2,0) should be blocked by AI
        }

        var aiMove = -1
        ai.scheduleAIMove(cells) { move ->
            aiMove = move
        }

        testDispatcher.scheduler.advanceTimeBy(AI_MOVE_DELAY_IN_MILLIS + 1)

        assertEquals(6, aiMove, "AI should block human win at position 6")
    }

    @Test
    fun blockHumanWinInDiagonal() = runTest {
        // X has two in main diagonal, AI should block
        val cells = listOfEmptyCells().toMutableList().apply {
            this[0] = this[0].copy(value = X) // X at (0,0)
            this[4] = this[4].copy(value = X) // X at (1,1)
            // Position 8 (2,2) should be blocked by AI
        }

        var aiMove = -1
        ai.scheduleAIMove(cells) { move ->
            aiMove = move
        }

        testDispatcher.scheduler.advanceTimeBy(AI_MOVE_DELAY_IN_MILLIS + 1)

        assertEquals(8, aiMove, "AI should block human win at position 8")
    }

    @Test
    fun winWhenPossible() = runTest {
        // AI (O) has two in a row, should complete the win
        val cells = listOfEmptyCells().toMutableList().apply {
            this[0] = this[0].copy(value = O) // O at (0,0)
            this[1] = this[1].copy(value = O) // O at (0,1)
            this[3] = this[3].copy(value = X) // X at (1,0)
            // Position 2 (0,2) should be taken by AI to win
        }

        var aiMove = -1
        ai.scheduleAIMove(cells) { move ->
            aiMove = move
        }

        testDispatcher.scheduler.advanceTimeBy(AI_MOVE_DELAY_IN_MILLIS + 1)

        assertEquals(2, aiMove, "AI should win at position 2")
    }

    @Test
    fun prioritizeWinOverBlock() = runTest {
        // Both AI can win and block human, should prioritize win
        val cells = listOfEmptyCells().toMutableList().apply {
            this[0] = this[0].copy(value = O) // O at (0,0)
            this[1] = this[1].copy(value = O) // O at (0,1)
            // O can win at position 2
            
            this[3] = this[3].copy(value = X) // X at (1,0)
            this[6] = this[6].copy(value = X) // X at (2,0)
            // X can win at position 6, but AI should prioritize own win
        }

        var aiMove = -1
        ai.scheduleAIMove(cells) { move ->
            aiMove = move
        }

        testDispatcher.scheduler.advanceTimeBy(AI_MOVE_DELAY_IN_MILLIS + 1)

        assertEquals(2, aiMove, "AI should prioritize winning over blocking")
    }

    @Test
    fun takeCenterWhenEmpty() = runTest {
        // Empty board, AI should prefer center
        val cells = listOfEmptyCells().toMutableList().apply {
            this[0] = this[0].copy(value = X) // X takes corner
        }

        var aiMove = -1
        ai.scheduleAIMove(cells) { move ->
            aiMove = move
        }

        testDispatcher.scheduler.advanceTimeBy(AI_MOVE_DELAY_IN_MILLIS + 1)

        assertEquals(4, aiMove, "AI should take center when available")
    }

    @Test
    fun takeCornerWhenCenterTaken() = runTest {
        // Center taken, AI should take corner
        val cells = listOfEmptyCells().toMutableList().apply {
            this[4] = this[4].copy(value = X) // X takes center
        }

        var aiMove = -1
        ai.scheduleAIMove(cells) { move ->
            aiMove = move
        }

        testDispatcher.scheduler.advanceTimeBy(AI_MOVE_DELAY_IN_MILLIS + 1)

        // AI should take a corner (0, 2, 6, or 8)
        val corners = listOf(0, 2, 6, 8)
        assertTrue(
            corners.contains(aiMove),
            "AI should take a corner when center is taken, but took $aiMove"
        )
    }

    @Test
    fun makeValidMoveOnlyInEmptyCells() = runTest {
        // Test that AI only moves to empty cells
        val cells = listOfEmptyCells().toMutableList().apply {
            this[0] = this[0].copy(value = X)
            this[1] = this[1].copy(value = O)
            this[2] = this[2].copy(value = X)
            this[3] = this[3].copy(value = O)
            this[4] = this[4].copy(value = X)
            // Positions 5, 6, 7, 8 are empty
        }

        var aiMove = -1
        ai.scheduleAIMove(cells) { move ->
            aiMove = move
        }

        testDispatcher.scheduler.advanceTimeBy(AI_MOVE_DELAY_IN_MILLIS + 1)

        val emptyCells = listOf(5, 6, 7, 8)
        assertTrue(
            emptyCells.contains(aiMove),
            "AI should only move to empty cells, but moved to $aiMove"
        )
    }

    @Test
    fun handleNearDrawScenario() = runTest {
        // Board almost full, AI should make best available move
        val cells = listOfEmptyCells().toMutableList().apply {
            this[0] = this[0].copy(value = X)
            this[1] = this[1].copy(value = O)
            this[2] = this[2].copy(value = X)
            this[3] = this[3].copy(value = X)
            this[4] = this[4].copy(value = O)
            this[5] = this[5].copy(value = O)
            this[6] = this[6].copy(value = O)
            this[7] = this[7].copy(value = X)
            // Only position 8 is empty
        }

        var aiMove = -1
        ai.scheduleAIMove(cells) { move ->
            aiMove = move
        }

        testDispatcher.scheduler.advanceTimeBy(AI_MOVE_DELAY_IN_MILLIS + 1)

        assertEquals(8, aiMove, "AI should take the only available move")
    }

    @Test
    fun preventForkingStrategy() = runTest {
        // Human is setting up a fork (two ways to win), AI should prevent it
        val cells = listOfEmptyCells().toMutableList().apply {
            this[0] = this[0].copy(value = X) // X at corner
            this[8] = this[8].copy(value = X) // X at opposite corner
            this[4] = this[4].copy(value = O) // O at center
            // X is setting up fork, AI should block appropriately
        }

        var aiMove = -1
        ai.scheduleAIMove(cells) { move ->
            aiMove = move
        }

        testDispatcher.scheduler.advanceTimeBy(AI_MOVE_DELAY_IN_MILLIS + 1)

        // AI should take an edge to prevent fork
        val edges = listOf(1, 3, 5, 7)
        assertTrue(
            edges.contains(aiMove),
            "AI should take an edge to prevent fork, but took $aiMove"
        )
    }

    @Test
    fun cancelPreviousMoveWhenNewMoveScheduled() = runTest {
        val cells1 = listOfEmptyCells()
        val cells2 = listOfEmptyCells().toMutableList().apply {
            this[0] = this[0].copy(value = X)
        }

        var firstMoveCallback = false
        var secondMoveCallback = false

        // Schedule first move
        ai.scheduleAIMove(cells1) { 
            firstMoveCallback = true
        }

        // Immediately schedule second move (should cancel first)
        ai.scheduleAIMove(cells2) { 
            secondMoveCallback = true
        }

        testDispatcher.scheduler.advanceTimeBy(AI_MOVE_DELAY_IN_MILLIS + 1)

        assertTrue(
            secondMoveCallback,
            "Second move callback should be executed"
        )
        assertTrue(
            !firstMoveCallback,
            "First move callback should be cancelled"
        )
    }

    @Test
    fun disposeProperlyCleanupResources() = runTest {
        val cells = listOfEmptyCells()
        var callbackExecuted = false

        ai.scheduleAIMove(cells) { 
            callbackExecuted = true
        }

        // Dispose before move completes
        ai.dispose()

        testDispatcher.scheduler.advanceTimeBy(AI_MOVE_DELAY_IN_MILLIS + 1)

        assertTrue(
            !callbackExecuted,
            "Callback should not be executed after dispose"
        )
    }

    @Test
    fun makeOptimalMovesInComplexScenario() = runTest {
        // Complex board where AI needs to think ahead
        val cells = listOfEmptyCells().toMutableList().apply {
            this[4] = this[4].copy(value = X) // X takes center
            this[0] = this[0].copy(value = O) // O takes corner
            this[8] = this[8].copy(value = X) // X takes opposite corner
        }

        var aiMove = -1
        ai.scheduleAIMove(cells) { move ->
            aiMove = move
        }

        testDispatcher.scheduler.advanceTimeBy(AI_MOVE_DELAY_IN_MILLIS + 1)

        // AI should make a strategic move (not losing move)
        assertNotEquals(-1, aiMove, "AI should make a valid move")
        val validMoves = listOf(1, 2, 3, 5, 6, 7)
        assertTrue(
            validMoves.contains(aiMove),
            "AI should make a valid strategic move"
        )
    }

    @Test
    fun recognizeImmediateDrawSituation() = runTest {
        // Board setup where optimal play leads to draw
        val cells = listOfEmptyCells().toMutableList().apply {
            this[0] = this[0].copy(value = X)
            this[1] = this[1].copy(value = O)
            this[2] = this[2].copy(value = X)
            this[3] = this[3].copy(value = X)
            this[4] = this[4].copy(value = O)
            this[5] = this[5].copy(value = O)
            this[6] = this[6].copy(value = O)
            this[7] = this[7].copy(value = X)
            // Position 8 left - this should result in draw
        }

        var aiMove = -1
        ai.scheduleAIMove(cells) { move ->
            aiMove = move
        }

        testDispatcher.scheduler.advanceTimeBy(AI_MOVE_DELAY_IN_MILLIS + 1)

        assertEquals(8, aiMove, "AI should take the last position")
    }

    @Test
    fun workWithDifferentBoardStates() = runTest {
        // Test with different starting positions
        val testCases = listOf(
            // X in different corners
            listOf(0),
            listOf(2), 
            listOf(6),
            listOf(8),
            // X in edges
            listOf(1),
            listOf(3),
            listOf(5),
            listOf(7),
            // Multiple X positions
            listOf(0, 4),
            listOf(1, 5)
        )

        testCases.forEach { xPositions ->
            val cells = listOfEmptyCells().toMutableList()
            xPositions.forEach { pos ->
                cells[pos] = cells[pos].copy(value = X)
            }

            var aiMove = -1
            ai.scheduleAIMove(cells) { move ->
                aiMove = move
            }

            testDispatcher.scheduler.advanceTimeBy(AI_MOVE_DELAY_IN_MILLIS + 1)

            assertTrue(
                aiMove in 0..8 && cells[aiMove].value == null,
                "AI should make valid move for X positions $xPositions, but chose $aiMove"
            )
        }
    }
}