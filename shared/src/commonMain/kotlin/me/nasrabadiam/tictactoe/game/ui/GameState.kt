package me.nasrabadiam.tictactoe.game.ui

import androidx.lifecycle.SavedStateHandle
import kotlinx.serialization.Serializable
import me.nasrabadiam.tictactoe.game.model.Cell
import me.nasrabadiam.tictactoe.game.model.GameMode
import me.nasrabadiam.tictactoe.game.model.GameResult
import me.nasrabadiam.tictactoe.game.model.Player
import me.nasrabadiam.tictactoe.game.model.utlis.getCellsListState
import me.nasrabadiam.tictactoe.game.model.utlis.listOfEmptyCells
import me.nasrabadiam.tictactoe.game.ui.ScoresState.Companion.DRAW_COUNT_KEY
import me.nasrabadiam.tictactoe.game.ui.ScoresState.Companion.O_SCORE_KEY
import me.nasrabadiam.tictactoe.game.ui.ScoresState.Companion.X_SCORE_KEY

@Serializable
data class GameState(
    val cells: List<Cell> = listOfEmptyCells(),
    val gameResult: GameResult? = null,
    val currentPlayer: Player = Player.X,
    val scores: ScoresState = ScoresState(),
    val gameMode: GameMode = GameMode.PLAYER_VS_PLAYER
) {

    companion object {

        fun getState(savedStateHandle: SavedStateHandle, gameMode: GameMode): GameState {
            return GameState(
                gameResult = GameResult.getState(savedStateHandle),
                cells = getCellsListState(savedStateHandle),
                currentPlayer = Player.getState(savedStateHandle),
                scores = ScoresState.getState(savedStateHandle),
                gameMode = GameMode.getState(savedStateHandle, gameMode),
            )
        }
    }
}

typealias XScore = Int
typealias OScore = Int
typealias DrawCount = Int

@Serializable
data class ScoresState(
    val xScore: XScore = 0,
    val oScore: OScore = 0,
    val drawCount: DrawCount = 0,
) {

    companion object {
        fun getState(savedStateHandle: SavedStateHandle): ScoresState {
            return ScoresState(
                xScore = savedStateHandle[X_SCORE_KEY] ?: 0,
                oScore = savedStateHandle[O_SCORE_KEY] ?: 0,
                drawCount = savedStateHandle[DRAW_COUNT_KEY] ?: 0
            )
        }

        internal const val DRAW_COUNT_KEY = "draw_count"
        internal const val X_SCORE_KEY = "x_score"
        internal const val O_SCORE_KEY = "o_score"
    }
}

fun XScore.saveXScore(savedStateHandle: SavedStateHandle) {
    savedStateHandle[X_SCORE_KEY] = this
}

fun OScore.saveOScore(savedStateHandle: SavedStateHandle) {
    savedStateHandle[O_SCORE_KEY] = this
}

fun DrawCount.saveDrawCount(savedStateHandle: SavedStateHandle) {
    savedStateHandle[DRAW_COUNT_KEY] = this
}