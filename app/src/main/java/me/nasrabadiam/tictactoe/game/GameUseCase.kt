package me.nasrabadiam.tictactoe.game

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import me.nasrabadiam.tictactoe.di.scopes.AppScope
import me.nasrabadiam.tictactoe.game.model.Cell
import me.nasrabadiam.tictactoe.game.model.DEFAULT_BOARD_CELL_COUNT
import me.nasrabadiam.tictactoe.game.model.GameResult
import me.nasrabadiam.tictactoe.game.model.Player
import me.nasrabadiam.tictactoe.game.model.utlis.getColumnIndex
import me.nasrabadiam.tictactoe.game.model.utlis.getRowIndex
import me.nasrabadiam.tictactoe.game.model.utlis.listOfEmptyCells
import me.tatarka.inject.annotations.Inject

@Inject
@AppScope
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

    private val _xScore: MutableStateFlow<Int> = MutableStateFlow(0)
    val xScore: StateFlow<Int> = _xScore

    private val _oScore: MutableStateFlow<Int> = MutableStateFlow(0)
    val oScore: StateFlow<Int> = _oScore

    private val _drawCount: MutableStateFlow<Int> = MutableStateFlow(0)
    val drawCount: StateFlow<Int> = _drawCount

    internal var currentPlayer: Player = starterPlayer
        private set

    fun clickOnCell(index: Int) {
        if (gameResult.value != null) return
        val copiedCells = cellsList
        if (index >= copiedCells.size || copiedCells[index].value != null) return
        copiedCells[index] = copiedCells[index].copy(value = currentPlayer)
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

        currentPlayer = starterPlayer
    }

    fun replayGame() {
        val newCellList = listOfEmptyCells(boardSize)
        _cells.update { newCellList }
        _gameResult.update { null }
    }

    private fun changePlayerTurn() {
        currentPlayer = if (currentPlayer == Player.X) {
            Player.O
        } else {
            Player.X
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
        val nonNullWinnerMap = winnerMap.entries.mapNotNull { it.value }

        if (nonNullWinnerMap.isNotEmpty()) {
            val winner = nonNullWinnerMap[0]
            updateWinnerScore(winner)
            _gameResult.update { GameResult.EndWithWinner(winner) }
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
            "first_cross"
        } else {
            null
        }
    }

    private fun getSecondCrossKey(rowIndex: Int, colIndex: Int): String? {
        return if (
            (rowIndex == 0 && colIndex == 2) ||
            (rowIndex == 1 && colIndex == 1) ||
            (rowIndex == 2 && colIndex == 0)
        ) {
            "second_cross"
        } else {
            null
        }
    }
}