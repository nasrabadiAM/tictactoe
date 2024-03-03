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
        val hashMap = mutableMapOf<String, Player?>()
        cellsList.forEachIndexed { index, cell ->
            val rowKey = getRowKey(cellsList.getRowIndex(index))
            val colKey = getColKey(cellsList.getColumnIndex(index))
            if (!hashMap.containsKey(rowKey)) {
                hashMap[rowKey] = cell.value
            }
            if (!hashMap.containsKey(colKey)) {
                hashMap[colKey] = cell.value
            }

            if (hashMap[rowKey] != cell.value) {
                hashMap[rowKey] = null
            }
            if (hashMap[colKey] != cell.value) {
                hashMap[colKey] = null
            }
        }
        val result = hashMap.entries.mapNotNull { it.value }

        if (result.isNotEmpty()) {
            _gameResult.update { result[0] }
        }
    }

    private fun getRowKey(rowIndex: Int): String {
        return "row_$rowIndex"
    }

    private fun getColKey(colIndex: Int): String {
        return "col_$colIndex"
    }
}