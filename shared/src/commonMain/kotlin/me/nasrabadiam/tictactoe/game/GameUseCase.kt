package me.nasrabadiam.tictactoe.game

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import me.nasrabadiam.tictactoe.game.model.Cell
import me.nasrabadiam.tictactoe.game.model.DEFAULT_BOARD_CELL_COUNT
import me.nasrabadiam.tictactoe.game.model.GameResult
import me.nasrabadiam.tictactoe.game.model.Player
import me.nasrabadiam.tictactoe.game.model.utlis.getColumnIndex
import me.nasrabadiam.tictactoe.game.model.utlis.getRowIndex
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
        if (gameResult.value != null) return
        val copiedCells = cellsList
        if (index >= copiedCells.size || copiedCells[index].value != null) return
        copiedCells[index] = copiedCells[index].copy(value = currentPlayer.value)
        _cells.update { copiedCells }
        changePlayerTurn()
        checkGameResultAndNotifyIfChanged()
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
        val winnerMap = mutableMapOf<String, Player?>()
        cellsList.forEachIndexed { index, cell ->
            val rowIndex = cellsList.getRowIndex(index)
            val colIndex = cellsList.getColumnIndex(index)

            // Row
            val rowKey = getRowKey(rowIndex)
            winnerMap.putInMap(rowKey, cell)

            // Column
            val colKey = getColKey(colIndex)
            winnerMap.putInMap(colKey, cell)

            // Left to right cross
            getFirstCrossKey(rowIndex, colIndex)?.let { key ->
                winnerMap.putInMap(key, cell)
            }

            // Right to left cross
            getSecondCrossKey(rowIndex, colIndex)?.let { key ->
                winnerMap.putInMap(key, cell)
            }
        }
        val nonNullWinnerMap = winnerMap.entries.filter { it.value != null }

        if (nonNullWinnerMap.isNotEmpty()) {
            val winner = requireNotNull(nonNullWinnerMap.first().value)
            val winningKey = nonNullWinnerMap.first().key
            updateWinnerScore(winner)
            val orientation = getOrientation(winningKey)
            val index = getWinningIndex(winningKey)
            _gameResult.update {
                GameResult.EndWithWinner(
                    player = winner,
                    winningOrientation = orientation,
                    winningIndex = index
                )
            }
            changePlayerTurn()
        } else if (hasNotAnyEmptyCell()) {
            _drawCount.update { drawCount.value + 1 }
            _gameResult.update { GameResult.Draw }
        }
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

    private fun MutableMap<String, Player?>.putInMap(
        key: String,
        cell: Cell
    ) {
        if (!containsKey(key)) {
            this[key] = cell.value
        }
        if (this[key] != cell.value) {
            this[key] = null
        }
    }

    private fun getRowKey(rowIndex: Int): String {
        return "row_$rowIndex"
    }

    private fun getColKey(colIndex: Int): String {
        return "col_$colIndex"
    }

    private fun getFirstCrossKey(rowIndex: Int, colIndex: Int): String? {
        return if (rowIndex == colIndex) {
            "cross_0"
        } else {
            null
        }
    }

    private fun getSecondCrossKey(rowIndex: Int, colIndex: Int): String? {
        return if (
            isSecondCrossCell(rowIndex, colIndex)
        ) {
            "cross_1"
        } else {
            null
        }
    }

    private fun getWinningIndex(winningKey: String): Int {
        val index = winningKey.split(WINNING_KEY_DELIMITER).getOrNull(1)
        return index?.toInt()
            ?: throw IllegalStateException(
                "There is no winning index with this " +
                    "(index value: $index), winningKey:$winningKey"
            )
    }

    private fun getOrientation(winningKey: String): Orientation {
        val orientation = winningKey.split(WINNING_KEY_DELIMITER).first()
        return when (orientation) {
            "cross" -> Orientation.CROSS
            "row" -> Orientation.ROW
            "col" -> Orientation.COLUMN
            else -> throw IllegalStateException(
                "There is no orientation with (name: $orientation)," +
                    " winningKey:$winningKey"
            )
        }
    }

    private fun isSecondCrossCell(rowIndex: Int, colIndex: Int): Boolean {
        return (rowIndex == 0 && colIndex == 2) ||
            (rowIndex == 1 && colIndex == 1) ||
            (rowIndex == 2 && colIndex == 0)
    }

    companion object {
        private const val WINNING_KEY_DELIMITER = "_"
    }

    enum class Orientation {
        CROSS, ROW, COLUMN
    }
}