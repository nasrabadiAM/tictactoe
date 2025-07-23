package me.nasrabadiam.tictactoe.game.ai

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import me.nasrabadiam.tictactoe.di.Named
import me.nasrabadiam.tictactoe.game.model.AIDifficulty
import me.nasrabadiam.tictactoe.game.model.AI_MOVE_DELAY_IN_MILLIS
import me.nasrabadiam.tictactoe.game.model.BOARD_SIZE
import me.nasrabadiam.tictactoe.game.model.Cell
import me.nasrabadiam.tictactoe.game.model.GameResult
import me.nasrabadiam.tictactoe.game.model.GameResult.Draw
import me.nasrabadiam.tictactoe.game.model.GameResult.EndWithWinner
import me.nasrabadiam.tictactoe.game.model.Player
import me.nasrabadiam.tictactoe.game.model.WiningOrientation
import me.nasrabadiam.tictactoe.test.mock.Mockable
import me.tatarka.inject.annotations.Inject
import kotlin.random.Random

@Inject
@Mockable
class TicTacToeAI(
    @Named("default") private val defaultDispatcher: CoroutineDispatcher,
) {
    // private val scope = CoroutineScope(defaultDispatcher + SupervisorJob())
    // private var aiMoveJob: Job? = null

    data class Move(val index: Int, val score: Int)

    /**
     * Schedule ai to make a move based on the difficulty level
     * @param cells Current board state
     * @param difficulty AI difficulty level
     * @param aiMoveCallback Callback to be called when the AI makes a move which @return aiMove Index of the best move
     */
    suspend fun scheduleAIMove(
        cells: List<Cell>,
        difficulty: AIDifficulty,
        aiMoveCallback: (aiMove: Int) -> Unit,
    ) {
        // aiMoveJob?.cancel()

        // aiMoveJob = scope.launch {
            delay(AI_MOVE_DELAY_IN_MILLIS)

            val aiMove = getBestMove(cells, Player.O, difficulty)
            aiMoveCallback(aiMove)
        // }
    }

    /**
     * Gets the best move for the AI based on difficulty level
     * @param cells Current board state
     * @param aiPlayer The AI player (usually Player.O)
     * @param difficulty AI difficulty level
     * @return Index of the best move
     */
    private suspend fun getBestMove(
        cells: List<Cell>,
        aiPlayer: Player,
        difficulty: AIDifficulty,
    ): Int {
        val availableMoves = getAvailableMoves(cells)
        if (availableMoves.isEmpty()) return DEFAULT_MOVE_INDEX

        return when (difficulty) {
            AIDifficulty.EASY -> getEasyMove(cells, aiPlayer, availableMoves)
            AIDifficulty.NORMAL -> getNormalMove(cells, aiPlayer, availableMoves)
            AIDifficulty.HARD -> getHardMove(cells, aiPlayer)
        }
    }

    /**
     * Easy AI: Makes random moves with 20% chance of strategic play
     */
    private fun getEasyMove(
        cells: List<Cell>,
        aiPlayer: Player,
        availableMoves: List<Int>,
    ): Int {
        // 20% chance to play strategically, 80% random
        return if (Random.nextFloat() < EASY_MOVE_STRATEGIC_CHANCE) {
            // Check for immediate win or block only
            checkImmediateWinOrBlock(cells, aiPlayer, availableMoves) ?: availableMoves.random()
        } else {
            availableMoves.random()
        }
    }

    /**
     * Normal AI: Balanced strategy with some mistakes
     * - Always wins when possible
     * - Usually blocks opponent wins
     * - Sometimes makes suboptimal moves
     */
    private fun getNormalMove(
        cells: List<Cell>,
        aiPlayer: Player,
        availableMoves: List<Int>
    ): Int {
        // Always check for immediate win
        checkForWin(cells, aiPlayer, availableMoves)?.let { return it }
        val blockMove = checkForBlock(cells, aiPlayer.opposite(), availableMoves)
        return if (Random.nextFloat() < NORMAL_MOVE_BLOCK_COMPONENT_CHANCE && blockMove != null) {
            // 85% chance to block opponent win
            blockMove
        } else if (Random.nextFloat() < NORMAL_MOVE_MINIMAX_CHANCE) {
            // 70% chance to use minimax with limited depth, otherwise random strategic move
            val bestMove = minimax(
                cells = cells,
                currentPlayer = aiPlayer,
                aiPlayer = aiPlayer,
                depth = 0,
                alpha = Int.MIN_VALUE,
                beta = Int.MAX_VALUE,
                maxDepth = LIMITED_MAX_DEPTH,
            )
            bestMove.index
        } else {
            getStrategicMove(availableMoves)
        }
    }

    /**
     * Hard AI: Uses full minimax algorithm (unbeatable)
     */
    private fun getHardMove(cells: List<Cell>, aiPlayer: Player): Int {
        val bestMove = minimax(cells, aiPlayer, aiPlayer, 0, Int.MIN_VALUE, Int.MAX_VALUE)
        return bestMove.index
    }

    /**
     * Check for immediate win or block opportunity
     */
    private fun checkImmediateWinOrBlock(
        cells: List<Cell>,
        aiPlayer: Player,
        availableMoves: List<Int>,
    ): Int? {
        // Check for win first
        checkForWin(cells, aiPlayer, availableMoves)?.let { return it }

        // Then check for block
        return checkForBlock(cells, aiPlayer.opposite(), availableMoves)
    }

    /**
     * Check if AI can win in one move
     */
    private fun checkForWin(
        cells: List<Cell>,
        player: Player,
        availableMoves: List<Int>,
    ): Int? {
        for (move in availableMoves) {
            val testCells = cells.mapIndexed { index, cell ->
                if (index == move) cell.copy(value = player) else cell
            }
            if (evaluateBoard(testCells) is GameResult.EndWithWinner) {
                return move
            }
        }
        return null
    }

    /**
     * Check if AI needs to block opponent from winning
     */
    private fun checkForBlock(
        cells: List<Cell>,
        opponent: Player,
        availableMoves: List<Int>,
    ): Int? {
        return checkForWin(cells, opponent, availableMoves)
    }

    /**
     * Make a strategic move (prefer center, then corners, then edges)
     */
    private fun getStrategicMove(
        availableMoves: List<Int>
    ): Int {
        val corners = CORNERS_INDEX.filter { it in availableMoves }
        return if (CENTER_INDEX in availableMoves) { // Prefer center
            CENTER_INDEX
        } else if (corners.isNotEmpty()) { // Then corners
            corners.random()
        } else { // Finally edges
            availableMoves.random()
        }
    }

    fun dispose() {
        // aiMoveJob?.cancel()
    }

    /**
     * Minimax algorithm with alpha-beta pruning
     * @param cells Current board state
     * @param currentPlayer Current player making the move
     * @param aiPlayer The AI player
     * @param depth Current depth in the game tree
     * @param alpha Alpha value for pruning
     * @param beta Beta value for pruning
     * @param maxDepth Maximum search depth (optional, for limited difficulty)
     * @return Best move with its score
     */
    @Suppress("ReturnCount", "MagicNumber")
    private fun minimax(
        cells: List<Cell>,
        currentPlayer: Player,
        aiPlayer: Player,
        depth: Int,
        alpha: Int,
        beta: Int,
        maxDepth: Int = Int.MAX_VALUE
    ): Move {
        val humanPlayer = aiPlayer.opposite()
        // Check depth limit for limited difficulty
        if (depth >= maxDepth) {
            return Move(DEFAULT_MOVE_INDEX, DEFAULT_SCORE)
        } // Neutral score when depth limit reached
        // Check terminal states
        val gameResult = evaluateBoard(cells)
        getEndStateMove(gameResult, aiPlayer, depth, humanPlayer)?.let {
            return it
        }
        val availableMoves = getAvailableMoves(cells)
        if (availableMoves.isEmpty()) return Move(DEFAULT_MOVE_INDEX, DEFAULT_SCORE)
        var bestMove = Move(
            DEFAULT_MOVE_INDEX,
            if (currentPlayer == aiPlayer) Int.MIN_VALUE else Int.MAX_VALUE
        )
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
                currentBeta,
                maxDepth
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

    private fun getEndStateMove(
        gameResult: GameResult?,
        aiPlayer: Player,
        depth: Int,
        humanPlayer: Player
    ) = when {
        gameResult is EndWithWinner -> {
            when (gameResult.player) {
                aiPlayer -> Move(
                    index = DEFAULT_MOVE_INDEX,
                    score = DEPTH_PENALTY_REWARD_VALUE - depth
                ) // AI wins (prefer quicker wins)
                humanPlayer -> Move(
                    index = DEFAULT_MOVE_INDEX,
                    score = depth - DEPTH_PENALTY_REWARD_VALUE
                ) // Human wins (prefer later losses)
                else -> Move(index = DEFAULT_MOVE_INDEX, score = DEFAULT_SCORE) // Should not happen
            }
        }

        gameResult is Draw -> Move(DEFAULT_MOVE_INDEX, DEFAULT_SCORE) // Draw
        else -> null
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
            val orientation: WiningOrientation,
            val index: Int
        )

        private const val DEFAULT_MOVE_INDEX = -1
        private const val DEFAULT_SCORE = 0
        private val CORNERS_INDEX = listOf(0, 2, 6, 8)
        private const val CENTER_INDEX = 4
        private const val NORMAL_MOVE_BLOCK_COMPONENT_CHANCE = 0.85f
        private const val NORMAL_MOVE_MINIMAX_CHANCE = 0.7f
        private const val EASY_MOVE_STRATEGIC_CHANCE = 0.2f
        private const val LIMITED_MAX_DEPTH = 4
        private const val DEPTH_PENALTY_REWARD_VALUE = 10

        private val WINNING_PATTERNS = buildList {

            // Rows (0-2)
            repeat(BOARD_SIZE) { row ->
                val cells = (0 until BOARD_SIZE).map { col -> row * BOARD_SIZE + col }
                add(WinPattern(cells, WiningOrientation.ROW, row))
            }

            // Columns (0-2)
            repeat(BOARD_SIZE) { col ->
                val cells = (0 until BOARD_SIZE).map { row -> row * BOARD_SIZE + col }
                add(WinPattern(cells, WiningOrientation.COLUMN, col))
            }

            // Main diagonal (top-left to bottom-right)
            val mainDiagonal = (0 until BOARD_SIZE).map { i -> i * BOARD_SIZE + i }
            add(WinPattern(mainDiagonal, WiningOrientation.CROSS, 0))

            // Anti-diagonal (top-right to bottom-left)
            val antiDiagonal = (0 until BOARD_SIZE).map { i ->
                i * BOARD_SIZE + (BOARD_SIZE - 1 - i)
            }
            add(WinPattern(antiDiagonal, WiningOrientation.CROSS, 1))
        }
    }
}