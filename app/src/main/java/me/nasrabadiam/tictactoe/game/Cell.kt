package me.nasrabadiam.tictactoe.game

data class Cell(
    val index: Int,
    val value: Player? = null
) {
    fun getShowingValue(): String = value?.toString() ?: " "
}