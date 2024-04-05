package me.nasrabadiam.tictactoe.game.model

import java.io.Serializable

data class Cell(
    val index: Int,
    val value: Player? = null
) : Serializable {
    fun getShowingValue(): String = value?.toString() ?: " "
}