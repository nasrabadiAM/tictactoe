package me.nasrabadiam.tictactoe.game.model

data class Cell(
    val index: Int,
    val value: Player? = null
) {
    fun getShowingValue(): String = value?.toString() ?: " "
}