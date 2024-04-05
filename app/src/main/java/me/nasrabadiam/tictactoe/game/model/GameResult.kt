package me.nasrabadiam.tictactoe.game.model

import java.io.Serializable

sealed class GameResult : Serializable {
    data class EndWithWinner(val player: Player) : GameResult()
    data object Draw : GameResult()
}