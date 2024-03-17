package me.nasrabadiam.tictactoe.game.model.utlis

import me.nasrabadiam.tictactoe.game.model.Cell
import me.nasrabadiam.tictactoe.game.model.DEFAULT_BOARD_CELL_COUNT
import kotlin.math.sqrt

public fun listOfEmptyCells(size: Int): List<Cell> {
    val listOf = mutableListOf<Cell>()
    return listOf.apply {
        repeat(size) { add(Cell(it)) }
    }
}

public fun <T> Collection<T>.getBoardSize(): Int {
    return getBoardSize(this.size)
}

public fun getBoardSize(cellCount: Int): Int {
    return sqrt(cellCount.toDouble()).toInt()
}

public fun Collection<Cell>.getCellIndex(row: Int, col: Int): Int {
    return getCellIndex(row, col, this.size)
}

public fun getCellIndex(row: Int, col: Int, cellCounts: Int = DEFAULT_BOARD_CELL_COUNT): Int {
    val boardSize = getBoardSize(cellCounts)
    return row * boardSize + col
}