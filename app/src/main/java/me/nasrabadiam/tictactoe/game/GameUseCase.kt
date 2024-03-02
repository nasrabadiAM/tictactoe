package me.nasrabadiam.tictactoe.game

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import me.nasrabadiam.tictactoe.game.utlis.getBoardSize
import me.nasrabadiam.tictactoe.game.utlis.getCellIndex
import me.nasrabadiam.tictactoe.game.utlis.listOfEmptyCells

class GameUseCase(
    boardSize: Int = DEFAULT_BOARD_CELL_COUNT,
    starterPlayer: Players = Players.X
) {
    private val _cells: MutableStateFlow<List<Cell>> =
        MutableStateFlow(listOfEmptyCells(boardSize))

    val cells: StateFlow<List<Cell>> = _cells
    private val cellsList get() = _cells.value.toMutableList()

    private val _gameResult: MutableStateFlow<Players?> = MutableStateFlow(null)
    val gameResult: StateFlow<Players?> = _gameResult

    internal var currentPlayer: String = starterPlayer.toString()
        private set

    fun onCellClicked(index: Int) {
        val copiedCells = cellsList
        if (index >= copiedCells.size || copiedCells[index].value.isNotEmpty()) return
        copiedCells[index] = copiedCells[index].copy(value = currentPlayer)
        _cells.update { copiedCells }
        changePlayerTurn()
        checkGameResult()
    }

    private fun changePlayerTurn() {
        currentPlayer = if (currentPlayer == Players.X.toString()) {
            Players.O.toString()
        } else {
            Players.X.toString()
        }
    }

    private fun checkGameResult() {
        val boardSize = cellsList.getBoardSize()
        for (columnIndex in 0 until boardSize) {
            var lastValue: String? = null
            for (rowIndex in 0 until boardSize) {
                val value = cellsList[getCellIndex(rowIndex, columnIndex)].value
                if (value.isNotEmpty()) {
                    if (lastValue == null) {
                        lastValue = value
                        continue
                    } else if (lastValue == value) {
                        if (rowIndex == boardSize - 1) {
                            val player = if (Players.X.toString() == lastValue) {
                                Players.X
                            } else {
                                Players.O
                            }
                            _gameResult.update { player }
                        }
                        continue
                    } else {
                        break
                    }
                } else {
                    break
                }
            }
        }
    }
}