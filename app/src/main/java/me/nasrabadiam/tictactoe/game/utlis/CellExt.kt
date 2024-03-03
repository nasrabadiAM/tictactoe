package me.nasrabadiam.tictactoe.game.utlis

import me.nasrabadiam.tictactoe.game.Cell

fun List<Cell>.getRowIndex(listIndex: Int): Int {
    val boardSize = getBoardSize()
    return listIndex / boardSize
}

fun List<Cell>.getColumnIndex(listIndex: Int): Int {
    val boardSize = getBoardSize()
    return listIndex % boardSize
}