package me.nasrabadiam.tictactoe.game

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import me.nasrabadiam.tictactoe.game.Players.O
import me.nasrabadiam.tictactoe.game.Players.X
import me.nasrabadiam.tictactoe.game.utlis.listOfEmptyCells

class GameUseCase(
    boardSize: Int = DEFAULT_BOARD_SIZE,
    starterPlayer: Players = X
) {
    private val _cells: MutableStateFlow<List<Cell>> =
        MutableStateFlow(listOfEmptyCells(boardSize))

    val cells: StateFlow<List<Cell>> = _cells
    private val cellsList get() = _cells.value.toMutableList()

    internal var currentPlayer: String = starterPlayer.toString()
        private set

    fun onCellClicked(index: Int) {
        val copiedCells = cellsList
        if (index >= copiedCells.size || copiedCells[index].value.isNotEmpty()) return
        copiedCells[index] = copiedCells[index].copy(value = currentPlayer)
        _cells.update { copiedCells }
        changePlayerTurn()
    }

    private fun changePlayerTurn() {
        currentPlayer = if (currentPlayer == X.toString()) {
            O.toString()
        } else {
            X.toString()
        }
    }
}