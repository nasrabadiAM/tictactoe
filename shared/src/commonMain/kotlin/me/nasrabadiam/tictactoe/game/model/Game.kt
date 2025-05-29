package me.nasrabadiam.tictactoe.game.model

import kotlinx.serialization.Serializable

@Serializable
data class Game(val gameMode: GameMode)
