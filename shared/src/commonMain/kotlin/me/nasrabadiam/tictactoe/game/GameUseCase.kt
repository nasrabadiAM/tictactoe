package me.nasrabadiam.tictactoe.game

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import me.nasrabadiam.tictactoe.game.model.BOARD_SIZE
import me.nasrabadiam.tictactoe.game.model.Cell
import me.nasrabadiam.tictactoe.game.model.DEFAULT_BOARD_CELL_COUNT
import me.nasrabadiam.tictactoe.game.model.GameResult
import me.nasrabadiam.tictactoe.game.model.Player
import me.nasrabadiam.tictactoe.game.model.utlis.listOfEmptyCells
import me.nasrabadiam.tictactoe.game.ui.DrawCount
import me.nasrabadiam.tictactoe.game.ui.GameState
import me.nasrabadiam.tictactoe.game.ui.OScore
import me.nasrabadiam.tictactoe.game.ui.XScore
import me.nasrabadiam.tictactoe.test.mock.Mockable
import me.tatarka.inject.annotations.Inject

@Inject
@Mockable
class GameUseCase(
    private val boardSize: Int = DEFAULT_BOARD_CELL_COUNT,
    private val starterPlayer: Player = Player.X
) {
    private val _cells: MutableStateFlow<List<Cell>> =
        MutableStateFlow(listOfEmptyCells(boardSize))

    val cells: StateFlow<List<Cell>> = _cells
    private val cellsList get() = _cells.value.toMutableList()

    private val _gameResult: MutableStateFlow<GameResult?> = MutableStateFlow(null)
    val gameResult: StateFlow<GameResult?> = _gameResult

    private val _xScore: MutableStateFlow<XScore> = MutableStateFlow(0)
    val xScore: StateFlow<XScore> = _xScore

    private val _oScore: MutableStateFlow<OScore> = MutableStateFlow(0)
    val oScore: StateFlow<OScore> = _oScore

    private val _drawCount: MutableStateFlow<DrawCount> = MutableStateFlow(0)
    val drawCount: StateFlow<DrawCount> = _drawCount

    private val _currentPlayer: MutableStateFlow<Player> = MutableStateFlow(starterPlayer)
    val currentPlayer: StateFlow<Player> = _currentPlayer

    fun clickOnCell(index: Int) {
        if (gameResult.value != null || !isValidMove(index)) return

        _cells.update { cells ->
            cells.mapIndexed { i, cell ->
                if (i == index) cell.copy(value = currentPlayer.value) else cell
            }
        }
        changePlayerTurn()
        checkGameResultAndNotifyIfChanged()
    }

    private fun isValidMove(index: Int): Boolean {
        val cells = _cells.value
        return index in cells.indices && cells[index].value == null
    }

    fun restartGame() {
        val newCellList = listOfEmptyCells(boardSize)
        _cells.update { newCellList }
        _gameResult.update { null }

        _xScore.update { 0 }
        _oScore.update { 0 }
        _drawCount.update { 0 }

        _currentPlayer.update { starterPlayer }
    }

    fun replayGame() {
        val newCellList = listOfEmptyCells(boardSize)
        _cells.update { newCellList }
        _gameResult.update { null }
    }

    fun restoreGameState(gameState: GameState) {
        _gameResult.update { gameState.gameResult }
        _cells.update { gameState.cells }
        _xScore.update { gameState.scores.xScore }
        _currentPlayer.update { gameState.currentPlayer }
        _oScore.update { gameState.scores.oScore }
        _drawCount.update { gameState.scores.drawCount }
    }

    private fun changePlayerTurn() {
        _currentPlayer.update {
            if (currentPlayer.value == Player.X) {
                Player.O
            } else {
                Player.X
            }
        }
    }

    private fun checkGameResultAndNotifyIfChanged() {
        checkForWinner()?.let { winner ->
            updateWinnerScore(winner.player)
            _gameResult.update { winner }
            changePlayerTurn()
        } ?: run {
            if (hasNotAnyEmptyCell()) {
                _drawCount.update { drawCount.value + 1 }
                _gameResult.update { GameResult.Draw }
            }
        }
    }

    private fun checkForWinner(): GameResult.EndWithWinner? {
        val cells = _cells.value
        WINNING_PATTERNS.forEachIndexed { _, pattern ->
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
        return null
    }

    private fun updateWinnerScore(winner: Player) {
        if (winner == Player.X) {
            _xScore.update { xScore.value + 1 }
        } else {
            _oScore.update { oScore.value + 1 }
        }
    }

    private fun hasNotAnyEmptyCell(): Boolean {
        return cellsList.any { it.value == null }.not()
    }

    companion object {
        private data class WinPattern(
            val cells: List<Int>,
            val orientation: Orientation,
            val index: Int
        )

        private val WINNING_PATTERNS = buildList {
            // Rows (0-2)
            repeat(BOARD_SIZE) { row ->
                val cells = (0 until BOARD_SIZE).map { col ->
                    row * BOARD_SIZE + col
                }
                add(WinPattern(cells, Orientation.ROW, row))
            }

            // Columns (0-2)
            repeat(BOARD_SIZE) { col ->
                val cells = (0 until BOARD_SIZE).map { row ->
                    row * BOARD_SIZE + col
                }
                add(WinPattern(cells, Orientation.COLUMN, col))
            }

            // Main diagonal (top-left to bottom-right)
            val mainDiagonal = (0 until BOARD_SIZE).map { i ->
                i * BOARD_SIZE + i
            }
            add(WinPattern(mainDiagonal, Orientation.CROSS, 0))

            // Anti-diagonal (top-right to bottom-left)
            val antiDiagonal = (0 until BOARD_SIZE).map { i ->
                i * BOARD_SIZE + (BOARD_SIZE - 1 - i)
            }
            add(WinPattern(antiDiagonal, Orientation.CROSS, 1))
        }
    }

    enum class Orientation {
        CROSS, ROW, COLUMN
    }
}