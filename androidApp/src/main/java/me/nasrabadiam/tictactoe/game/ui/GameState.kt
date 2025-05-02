package me.nasrabadiam.tictactoe.game.ui

import me.nasrabadiam.tictactoe.game.model.Cell
import me.nasrabadiam.tictactoe.game.model.GameResult
import me.nasrabadiam.tictactoe.game.model.Player
import me.nasrabadiam.tictactoe.game.model.utlis.listOfEmptyCells
import java.io.Serializable

data class GameState(
    val cells: List<Cell> = listOfEmptyCells(),
    val gameResult: GameResult? = null,
    val currentPlayer: Player = Player.X,
    val scores: ScoresState = ScoresState()
) : Serializable

data class ScoresState(
    val xScore: Int = 0,
    val oScore: Int = 0,
    val drawCount: Int = 0,
) : Serializable