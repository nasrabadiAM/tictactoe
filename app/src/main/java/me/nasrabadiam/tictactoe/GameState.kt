package me.nasrabadiam.tictactoe

import me.nasrabadiam.tictactoe.game.model.Cell
import me.nasrabadiam.tictactoe.game.model.DEFAULT_BOARD_CELL_COUNT
import me.nasrabadiam.tictactoe.game.model.GameResult
import me.nasrabadiam.tictactoe.game.model.utlis.listOfEmptyCells

data class GameState(
    val cells: List<Cell> = listOfEmptyCells(DEFAULT_BOARD_CELL_COUNT),
    val gameResult: GameResult? = null,
    val scores: ScoresState = ScoresState()
)

data class ScoresState(
    val xScore: Int = 0,
    val oScore: Int = 0,
    val drawCount: Int = 0,
)