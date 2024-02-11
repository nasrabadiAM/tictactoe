package me.nasrabadiam.tictactoe

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun greetingTest(): Unit = with(composeRule) {
        setContent { Greeting(name = "Android") }
        onNodeWithText("Hello Android!").assertExists()
    }
}