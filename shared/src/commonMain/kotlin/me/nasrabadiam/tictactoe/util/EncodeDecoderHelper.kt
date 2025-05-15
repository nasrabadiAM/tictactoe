package me.nasrabadiam.tictactoe.util

import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.json.Json

object Encoder {
    fun <T> encodeToString(serializer: SerializationStrategy<T>, value: T): String {
        return Json.encodeToString(
            serializer,
            value
        )
    }
}

object Decoder {
    fun <T> decodeString(serializer: DeserializationStrategy<T>, input: String?): T? {
        return if (input == null) {
            null
        } else {
            Json.decodeFromString(serializer, input)
        }
    }
}