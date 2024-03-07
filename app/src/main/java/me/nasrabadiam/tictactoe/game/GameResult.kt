package me.nasrabadiam.tictactoe.game

sealed class GameResult {
    data class EndWithWinner(val player: Player) : GameResult()
    data object Draw : GameResult()
}