package me.nasrabadiam.tictactoe.ui

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.runComposeUiTest
import me.nasrabadiam.tictactoe.App
import me.nasrabadiam.tictactoe.game.GameUseCase
import me.nasrabadiam.tictactoe.ui.home.HomeScreenTests.Companion.PLAY_WITH_A_FRIEND_BUTTON_TEXT
import kotlin.test.Test

@OptIn(ExperimentalTestApi::class)
class NavigationTests {

    private val gameUseCase = GameUseCase()

    @Test
    fun whenClickedOnPlayWithAFriendShouldOpenGameScreenInSoloMode() = runComposeUiTest {
        setContent { App(gameUseCase = { gameUseCase }) }
        val playWithAFriendButton =
            onNode(hasContentDescription(PLAY_WITH_A_FRIEND_BUTTON_TEXT))
        playWithAFriendButton.performClick()
        playWithAFriendButton.assertDoesNotExist()

        // assert there is a restart button
        onNodeWithText(RESTART_GAME_BUTTON_TEXT).assertIsDisplayed()
    }

    @Test
    fun whenAppOpensShouldShowHomeRoute() = runComposeUiTest {
        setContent { App(gameUseCase = { gameUseCase }) }
        onNodeWithText(PLAY_WITH_A_FRIEND_BUTTON_TEXT).assertIsDisplayed()
    }
}
