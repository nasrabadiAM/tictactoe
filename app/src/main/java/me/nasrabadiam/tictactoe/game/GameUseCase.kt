package me.nasrabadiam.tictactoe.game

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import me.nasrabadiam.tictactoe.game.utlis.getColumnIndex
import me.nasrabadiam.tictactoe.game.utlis.getRowIndex
import me.nasrabadiam.tictactoe.game.utlis.listOfEmptyCells

class GameUseCase(
    boardSize: Int = DEFAULT_BOARD_CELL_COUNT,
    starterPlayer: Player = Player.X
) {
    private val _cells: MutableStateFlow<List<Cell>> =
        MutableStateFlow(listOfEmptyCells(boardSize))

    val cells: StateFlow<List<Cell>> = _cells
    private val cellsList get() = _cells.value.toMutableList()

    private val _gameResult: MutableStateFlow<Player?> = MutableStateFlow(null)
    val gameResult: StateFlow<Player?> = _gameResult

    internal var currentPlayer: Player = starterPlayer
        private set

    fun onCellClicked(index: Int) {
        if (gameResult.value != null) return
        val copiedCells = cellsList
        if (index >= copiedCells.size || copiedCells[index].value != null) return
        copiedCells[index] = copiedCells[index].copy(value = currentPlayer)
        _cells.update { copiedCells }
        changePlayerTurn()
        checkGameResultAndNotifyIfChanged()
    }

    private fun changePlayerTurn() {
        currentPlayer = if (currentPlayer == Player.X) {
            Player.O
        } else {
            Player.X
        }
    }

    private fun checkGameResultAndNotifyIfChanged() {
        val cellsMap = mutableMapOf<String, Player?>()
        cellsList.forEachIndexed { index, cell ->
            val rowIndex = cellsList.getRowIndex(index)
            val colIndex = cellsList.getColumnIndex(index)

            // Row
            val rowKey = getRowKey(rowIndex)
            cellsMap.putInMap(rowKey, cell)

            // Column
            val colKey = getColKey(colIndex)
            cellsMap.putInMap(colKey, cell)

            // Left to right cross
            getFirstCrossKey(rowIndex, colIndex)?.let { key ->
                cellsMap.putInMap(key, cell)
            }

            // Right to left cross
            getSecondCrossKey(rowIndex, colIndex)?.let { key ->
                cellsMap.putInMap(key, cell)
                println("rowIndex = $rowIndex, colIndex=$colIndex, key=$key")
            }
        }
        val result = cellsMap.entries.mapNotNull { it.value }

        if (result.isNotEmpty()) {
            _gameResult.update { result[0] }
        }
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