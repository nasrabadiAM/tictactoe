package me.nasrabadiam.tictactoe.game.ai

import me.nasrabadiam.tictactoe.game.GameUseCase
import me.nasrabadiam.tictactoe.game.model.BOARD_SIZE
import me.nasrabadiam.tictactoe.game.model.Cell
import me.nasrabadiam.tictactoe.game.model.GameResult
import me.nasrabadiam.tictactoe.game.model.Player
import me.nasrabadiam.tictactoe.test.mock.Mockable
import me.tatarka.inject.annotations.Inject

@Inject
@Mockable
class TicTacToeAI {

    data class Move(val index: Int, val score: Int)

    /**
     * Gets the best move for the AI using minimax algorithm
     * @param cells Current board state
     * @param aiPlayer The AI player (usually Player.O)
     * @return Index of the best move
     */
    fun getBestMove(cells: List<Cell>, aiPlayer: Player): Int {
        val bestMove = minimax(cells, aiPlayer, aiPlayer, 0, Int.MIN_VALUE, Int.MAX_VALUE)
        return bestMove.index
    }

    /**
     * Minimax algorithm with alpha-beta pruning
     * @param cells Current board state
     * @param currentPlayer Current player making the move
     * @param aiPlayer The AI player
     * @param depth Current depth in the game tree
     * @param alpha Alpha value for pruning
     * @param beta Beta value for pruning
     * @return Best move with its score
     */
    @Suppress("ReturnCount", "MagicNumber")
    private fun minimax(
        cells: List<Cell>,
        currentPlayer: Player,
        aiPlayer: Player,
        depth: Int,
        alpha: Int,
        beta: Int
    ): Move {
        val humanPlayer = aiPlayer.opposite()
        // Check terminal states
        val gameResult = evaluateBoard(cells)
        when {
            gameResult is GameResult.EndWithWinner -> {
                return when (gameResult.player) {
                    aiPlayer -> Move(-1, 10 - depth) // AI wins (prefer quicker wins)
                    humanPlayer -> Move(-1, depth - 10) // Human wins (prefer later losses)
                    else -> Move(-1, 0) // Should not happen
                }
            }

            gameResult is GameResult.Draw -> return Move(-1, 0) // Draw
        }

        val availableMoves = getAvailableMoves(cells)
        if (availableMoves.isEmpty()) return Move(-1, 0)

        var bestMove = Move(-1, if (currentPlayer == aiPlayer) Int.MIN_VALUE else Int.MAX_VALUE)
        var currentAlpha = alpha
        var currentBeta = beta

        for (moveIndex in availableMoves) {
            // Make the move
            val newCells = cells.mapIndexed { index, cell ->
                if (index == moveIndex) cell.copy(value = currentPlayer) else cell
            }

            // Recursive minimax call
            val moveScore = minimax(
                newCells,
                currentPlayer.opposite(),
                aiPlayer,
                depth + 1,
                currentAlpha,
                currentBeta
            ).score

            // Update best move based on player
            if (currentPlayer == aiPlayer) {
                // Maximizing player (AI)
                if (moveScore > bestMove.score) {
                    bestMove = Move(moveIndex, moveScore)
                }
                currentAlpha = maxOf(currentAlpha, moveScore)
            } else {
                // Minimizing player (Human)
                if (moveScore < bestMove.score) {
                    bestMove = Move(moveIndex, moveScore)
                }
                currentBeta = minOf(currentBeta, moveScore)
            }

            // Alpha-beta pruning
            if (currentBeta <= currentAlpha) {
                break
            }
        }

        return bestMove
    }

    /**
     * Get all available moves (empty cells)
     */
    private fun getAvailableMoves(cells: List<Cell>): List<Int> {
        return cells.mapIndexedNotNull { index, cell ->
            if (cell.value == null) index else null
        }
    }

    /**
     * Evaluate the current board state for terminal conditions
     */
    @Suppress("ReturnCount")
    private fun evaluateBoard(cells: List<Cell>): GameResult? {
        // Check all winning patterns
        WINNING_PATTERNS.forEach { pattern ->
            val values = pattern.cells.map { cells[it].value }
            val player = values.firstOrNull()
            if (player != null && values.all { it == player }) {
                return GameResult.EndWithWinner(
                    player = player,
                    winningOrientation = pattern.orientation,
                    winningIndex = pattern.index
                )
            }
        }

        // Check for draw
        if (cells.all { it.value != null }) {
            return GameResult.Draw
        }

        return null // Game still ongoing
    }

    companion object {
        // Reuse the winning patterns from GameUseCase
        private data class WinPattern(
            val cells: List<Int>,
            val orientation: GameUseCase.Orientation,
            val index: Int
        )

        private val WINNING_PATTERNS = buildList {

            // Rows (0-2)
            repeat(BOARD_SIZE) { row ->
                val cells = (0 until BOARD_SIZE).map { col -> row * BOARD_SIZE + col }
                add(WinPattern(cells, GameUseCase.Orientation.ROW, row))
            }

            // Columns (0-2)
            repeat(BOARD_SIZE) { col ->
                val cells = (0 until BOARD_SIZE).map { row -> row * BOARD_SIZE + col }
                add(WinPattern(cells, GameUseCase.Orientation.COLUMN, col))
            }

            // Main diagonal (top-left to bottom-right)
            val mainDiagonal = (0 until BOARD_SIZE).map { i -> i * BOARD_SIZE + i }
            add(WinPattern(mainDiagonal, GameUseCase.Orientation.CROSS, 0))

            // Anti-diagonal (top-right to bottom-left)
            val antiDiagonal = (0 until BOARD_SIZE).map { i ->
                i * BOARD_SIZE + (BOARD_SIZE - 1 - i)
            }
            add(WinPattern(antiDiagonal, GameUseCase.Orientation.CROSS, 1))
        }
    }
}