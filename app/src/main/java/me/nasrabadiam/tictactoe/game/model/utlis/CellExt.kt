package me.nasrabadiam.tictactoe.game.model.utlis

import me.nasrabadiam.tictactoe.game.model.Cell

fun List<Cell>.getRowIndex(listIndex: Int): Int {
    val boardSize = getBoardSize()
    return listIndex / boardSize
}

fun List<Cell>.getColumnIndex(listIndex: Int): Int {
    val boardSize = getBoardSize()
    return listIndex % boardSize
}