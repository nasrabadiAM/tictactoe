package me.nasrabadiam.tictactoe.game.model

import androidx.lifecycle.SavedStateHandle
import kotlinx.serialization.Serializable
import me.nasrabadiam.tictactoe.util.Decoder
import me.nasrabadiam.tictactoe.util.Encoder

@Serializable
sealed class GameMode {
    @Serializable
    data object PlayWithFriend : GameMode()

    @Serializable
    data class PlayWithAI(val difficulty: AIDifficulty = AIDifficulty.NORMAL) : GameMode()

    fun encode(): String {
        return Encoder.encodeToString(serializer(), this)
    }

    fun saveState(savedStateHandle: SavedStateHandle) {
        savedStateHandle[GAME_MODE_KEY] = this.encode()
    }

    companion object {

        fun getState(savedStateHandle: SavedStateHandle, default: GameMode): GameMode {
            return decode(savedStateHandle.get<String>(GAME_MODE_KEY)) ?: default
        }

        fun decode(input: String?): GameMode? {
            return Decoder.decodeString(serializer(), input)
        }

        private const val GAME_MODE_KEY = "game_mode"
    }
}