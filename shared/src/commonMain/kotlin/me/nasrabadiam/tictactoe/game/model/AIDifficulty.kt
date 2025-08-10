package me.nasrabadiam.tictactoe.game.model

import androidx.lifecycle.SavedStateHandle
import kotlinx.serialization.Serializable
import me.nasrabadiam.tictactoe.util.Decoder
import me.nasrabadiam.tictactoe.util.Encoder

@Serializable
enum class AIDifficulty {
    EASY,
    NORMAL,
    HARD;

    fun encode(): String {
        return Encoder.encodeToString(serializer(), this)
    }

    fun saveState(savedStateHandle: SavedStateHandle) {
        savedStateHandle[AI_DIFFICULTY_KEY] = this.encode()
    }

    companion object {
        fun getState(savedStateHandle: SavedStateHandle, default: AIDifficulty): AIDifficulty {
            return decode(savedStateHandle.get<String>(AI_DIFFICULTY_KEY)) ?: default
        }

        fun decode(input: String?): AIDifficulty? {
            return Decoder.decodeString(serializer(), input)
        }

        private const val AI_DIFFICULTY_KEY = "ai_difficulty"
    }
}