package me.nasrabadiam.tictactoe.game.model

import androidx.lifecycle.SavedStateHandle
import kotlinx.serialization.Serializable
import me.nasrabadiam.tictactoe.util.Decoder
import me.nasrabadiam.tictactoe.util.Encoder

@Serializable
enum class Player {
    X, O;

    private fun encode(): String {
        return Encoder.encodeToString(serializer(), this)
    }

    fun saveState(savedStateHandle: SavedStateHandle) {
        savedStateHandle[PLAYER_KEY] = this.encode()
    }

    companion object {

        fun getState(savedStateHandle: SavedStateHandle): Player {
            return decode(savedStateHandle.get<String>(PLAYER_KEY)) ?: X
        }

        private fun decode(input: String?): Player? {
            return Decoder.decodeString(serializer(), input)
        }

        private const val PLAYER_KEY = "player"
    }
}