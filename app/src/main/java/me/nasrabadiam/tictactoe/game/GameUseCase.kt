package me.nasrabadiam.tictactoe.game

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import me.nasrabadiam.tictactoe.game.utlis.getBoardSize
import me.nasrabadiam.tictactoe.game.utlis.getCellIndex
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
        val boardSize = cellsList.getBoardSize()
        var columnLastValue: Player? = null
        for (columnIndex in 0 until boardSize) {
            var rowLastValue: Player? = null

            for (rowIndex in 0 until boardSize) {
                val value = cellsList[getCellIndex(rowIndex, columnIndex)].value
                if (value != null) {
                    if (rowLastValue == null) {
                        rowLastValue = value
                        if (columnLastValue == null) {
                            columnLastValue = value
                        } else if (columnLastValue != value) {
                            columnLastValue = null
                        } else if (columnIndex == boardSize - 1) {
                            val player = if (Player.X == columnLastValue) {
                                Player.X
                            } else {
                                Player.O
                            }
                            _gameResult.update { player }
                        }
                        continue
                    } else if (rowLastValue == value) {
                        if (rowIndex == boardSize - 1) {
                            val player = if (Player.X == rowLastValue) {
                                Player.X
                            } else {
                                Player.O
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