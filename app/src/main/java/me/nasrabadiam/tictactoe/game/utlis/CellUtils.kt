package me.nasrabadiam.tictactoe.game.utlis

import me.nasrabadiam.tictactoe.game.Cell
import kotlin.math.sqrt

public fun listOfEmptyCells(size: Int): List<Cell> {
    val listOf = mutableListOf<Cell>()
    return listOf.apply {
        repeat(size) { add(Cell(it, "")) }
    }
}

public fun <T> Collection<T>.getBoardSize(): Int {
    return sqrt(this.size.toDouble()).toInt()
}