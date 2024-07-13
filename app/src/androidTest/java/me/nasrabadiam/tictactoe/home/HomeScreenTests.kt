package me.nasrabadiam.tictactoe.home

import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.performClick
import me.nasrabadiam.tictactoe.home.HomeEvent.PlayWithAFriend
import me.nasrabadiam.tictactoe.ui.getWindowSizeClass
import org.junit.Rule
import org.junit.Test

class HomeScreenTests {

    @get:Rule
    val composeRule = createComposeRule()

    private fun ComposeContentTestRule.setContent(
        homeEvent: (HomeEvent) -> Unit = {}
    ) {
        setContent {
            val windowSizeClass = getWindowSizeClass()
            HomeScreen(homeEvent, windowSizeClass)
        }
    }

    @Test
    fun whenOpenHomeScreenShouldShowAppIcon(): Unit = with(composeRule) {
        setContent()

        onNode(hasContentDescription("App Logo")).assertExists()
    }

    @Test
    fun whenOpenHomeScreenShouldShowPlayWithAFriendButton(): Unit = with(composeRule) {
        setContent()
        onNode(hasContentDescription(PLAY_WITH_A_FRIEND_BUTTON_TEXT)).assertExists()
    }

    @Test
    fun whenOpenHomeScreenShouldShowPlaySoloButtonDisabled(): Unit = with(composeRule) {
        setContent()
        val soloButton = onNode(hasContentDescription(PLAY_SOLO_BUTTON_TEXT))
        soloButton.assertExists()
        soloButton.assertIsNotEnabled()
    }

    @Test
    fun whenClickedOnPlaySoloShouldDoNothing(): Unit = with(composeRule) {
        setContent()
        val playSoloButton = onNode(hasContentDescription(PLAY_SOLO_BUTTON_TEXT))
        playSoloButton.performClick()
        playSoloButton.assertExists()
    }

    @Test
    fun whenClickedOnPlayWithAFriendShouldCallHomeEvent(): Unit = with(composeRule) {
        var clicked = false
        val homeEvent: (HomeEvent) -> Unit = {
            if (it == PlayWithAFriend) clicked = true
        }
        setContent(homeEvent)
        onNode(hasContentDescription(PLAY_WITH_A_FRIEND_BUTTON_TEXT)).performClick()
        assert(clicked) {
            "when clicked on play with a friend button," +
                " home event of play with a friend should call, but it doesn't called."
        }
    }

    companion object {
        internal const val PLAY_WITH_A_FRIEND_BUTTON_TEXT = "Play with a friend"
        private const val PLAY_SOLO_BUTTON_TEXT = "Play solo(coming soon)"
    }
}