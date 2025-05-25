package me.nasrabadiam.tictactoe.ui.home

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.runComposeUiTest
import me.nasrabadiam.tictactoe.GameWindowSizeClass.NORMAL
import me.nasrabadiam.tictactoe.home.HomeEvent
import me.nasrabadiam.tictactoe.home.HomeEvent.PlayWithAFriend
import me.nasrabadiam.tictactoe.home.HomeScreenRoute
import kotlin.test.Test
import kotlin.test.assertTrue

@OptIn(ExperimentalTestApi::class)
class HomeScreenTests {

    private val windowClass = NORMAL
    private val homeEvent: (HomeEvent) -> Unit = { }

    @Test
    fun whenOpenHomeScreenShouldShowAppIcon() = runComposeUiTest {
        setContent { HomeScreenRoute(homeEvent, windowClass) }

        onNode(hasContentDescription("App Logo")).assertExists()
    }

    @Test
    fun whenOpenHomeScreenShouldShowPlayWithAFriendButton() = runComposeUiTest {
        setContent { HomeScreenRoute(homeEvent, windowClass) }
        onNode(hasContentDescription(PLAY_WITH_A_FRIEND_BUTTON_TEXT)).assertExists()
    }

    @Test
    fun whenOpenHomeScreenShouldShowPlaySoloButtonDisabled() = runComposeUiTest {
        setContent { HomeScreenRoute(homeEvent, windowClass) }
        val soloButton = onNode(hasContentDescription(PLAY_SOLO_BUTTON_TEXT))
        soloButton.assertExists()
        soloButton.assertIsNotEnabled()
    }

    @Test
    fun whenClickedOnPlaySoloShouldDoNothing() = runComposeUiTest {
        setContent { HomeScreenRoute(homeEvent, windowClass) }
        val playSoloButton = onNode(hasContentDescription(PLAY_SOLO_BUTTON_TEXT))
        playSoloButton.performClick()
        playSoloButton.assertExists()
    }

    @Test
    fun whenClickedOnPlayWithAFriendShouldCallHomeEvent() = runComposeUiTest {
        var clicked = false
        val homeEvent: (HomeEvent) -> Unit = {
            if (it == PlayWithAFriend) clicked = true
        }
        setContent { HomeScreenRoute(homeEvent, windowClass) }
        onNode(hasContentDescription(PLAY_WITH_A_FRIEND_BUTTON_TEXT)).performClick()
        assertTrue(
            actual = clicked,
            message = "when clicked on play with a friend button," +
                " home event of play with a friend should call, but it doesn't called."
        )
    }

    companion object {
        internal const val PLAY_WITH_A_FRIEND_BUTTON_TEXT = "Play with a friend"
        private const val PLAY_SOLO_BUTTON_TEXT = "Play solo(coming soon)"
    }
}