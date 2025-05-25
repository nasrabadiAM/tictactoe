package me.nasrabadiam.tictactoe

import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer
import me.nasrabadiam.tictactoe.util.Decoder
import me.nasrabadiam.tictactoe.util.Encoder
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class EncoderDecoderTest {

    @Test
    fun testDecoderWhenInputIsNull() {
        val result = Decoder.decodeString(serializer<String>(), null)
        assertNull(result)
    }

    @Test
    fun testEncoderWhenInputIsValid() {
        val input = "test"
        val result = Encoder.encodeToString(serializer<String>(), input)

        assertEquals(Json.encodeToString(serializer<String>(), input), result)
    }

    @Test
    fun testDecoderWhenInputIsValid() {
        val inputValue = "test"
        val input = Json.encodeToString(serializer<String>(), inputValue)

        val result = Decoder.decodeString(serializer<String>(), input)

        assertEquals(inputValue, result)
    }
}