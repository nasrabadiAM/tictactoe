package me.nasrabadiam.tictactoe.game.model

import androidx.lifecycle.SavedStateHandle
import kotlinx.serialization.Serializable
import me.nasrabadiam.tictactoe.game.GameUseCase.Orientation
import me.nasrabadiam.tictactoe.util.Decoder
import me.nasrabadiam.tictactoe.util.Encoder

@Serializable
sealed class GameResult {
    @Serializable
    data class EndWithWinner(
        val player: Player,
        val winningOrientation: Orientation,
        val winningIndex: Int,
    ) : GameResult()

    @Serializable
    data object Draw : GameResult()

    fun saveState(savedStateHandle: SavedStateHandle) {
        savedStateHandle[GAME_RESULT_KEY] = this.encode()
    }

    private fun encode(): String {
        return Encoder.encodeToString(serializer(), this)
    }

    companion object {
        private const val GAME_RESULT_KEY = "game_result"
        private fun decode(input: String?): GameResult? {
            return Decoder.decodeString(serializer(), input)
        }

        fun getState(savedStateHandle: SavedStateHandle): GameResult? {
            return decode(savedStateHandle[GAME_RESULT_KEY])
        }
    }
}