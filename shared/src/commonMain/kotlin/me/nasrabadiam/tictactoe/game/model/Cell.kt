package me.nasrabadiam.tictactoe.game.model

import kotlinx.serialization.Serializable

@Serializable
data class Cell(
    val index: Int,
    val value: Player? = null
)