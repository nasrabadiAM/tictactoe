package me.nasrabadiam.tictactoe.home

import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.performClick
import me.nasrabadiam.tictactoe.GameWindowSizeClass.NORMAL
import org.junit.Rule
import org.junit.Test

class HomeScreenTests {

    @get:Rule
    val composeRule = createComposeRule()

    private val windowClass = NORMAL
    private val homeEvent: (HomeEvent) -> Unit = { }

    @Test
    fun whenOpenHomeScreenShouldShowAppIcon(): Unit = with(composeRule) {
        setContent { HomeScreen(homeEvent, windowClass) }

        onNode(hasContentDescription("App Logo")).assertExists()
    }

    @Test
    fun whenOpenHomeScreenShouldShowPlayWithAFriendButton(): Unit = with(composeRule) {
        setContent { HomeScreen(homeEvent, windowClass) }
        onNode(hasText(PLAY_WITH_A_FRIEND_BUTTON_TEXT)).assertExists()
    }

    @Test
    fun whenOpenHomeScreenShouldShowPlaySoloButtonDisabled(): Unit = with(composeRule) {
        setContent { HomeScreen(homeEvent, windowClass) }
        val soloButton = onNode(hasText(PLAY_SOLO_BUTTON_TEXT))
        soloButton.assertExists()
    }

    @Test
    fun whenClickedOnPlaySoloShouldCallHomeEvent(): Unit = with(composeRule) {
        var clicked = false
        val homeEvent: (HomeEvent) -> Unit = {
            if (it is HomeEvent.PlayWithAIEvent) clicked = true
        }
        setContent { HomeScreen(homeEvent, windowClass) }
        val playSoloButton = onNode(hasText(PLAY_SOLO_BUTTON_TEXT))
        playSoloButton.performClick()
        assert(clicked) {
            "when clicked on play with ai button," +
                " home event of play with ai should call, but it doesn't called."
        }
    }

    @Test
    fun whenClickedOnPlayWithAFriendShouldCallHomeEvent(): Unit = with(composeRule) {
        var clicked = false
        val homeEvent: (HomeEvent) -> Unit = {
            if (it is HomeEvent.PlayWithAFriendEvent) clicked = true
        }
        setContent { HomeScreen(homeEvent, windowClass) }
        onNode(hasText(PLAY_WITH_A_FRIEND_BUTTON_TEXT)).performClick()
        assert(clicked) {
            "when clicked on play with a friend button," +
                " home event of play with a friend should call, but it doesn't called."
        }
    }

    companion object {
        internal const val PLAY_WITH_A_FRIEND_BUTTON_TEXT = "Play with a friend"
        private const val PLAY_SOLO_BUTTON_TEXT = "Play with AI"
    }
}