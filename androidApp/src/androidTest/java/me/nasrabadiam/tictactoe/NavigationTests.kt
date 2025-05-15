package me.nasrabadiam.tictactoe

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import me.nasrabadiam.tictactoe.game.GameUseCase
import me.nasrabadiam.tictactoe.home.HomeScreenTests.Companion.PLAY_WITH_A_FRIEND_BUTTON_TEXT
import org.junit.Rule
import org.junit.Test

class NavigationTests {

    @get:Rule
    val composeRule = createComposeRule()

    private val gameUseCase = GameUseCase()

    @Test
    fun whenClickedOnPlayWithAFriendShouldOpenGameScreenInSoloMode(): Unit = with(composeRule) {
        setContent { App(gameUseCase = { gameUseCase }) }
        val playWithAFriendButton =
            onNode(hasContentDescription(PLAY_WITH_A_FRIEND_BUTTON_TEXT))
        playWithAFriendButton.performClick()
        playWithAFriendButton.assertDoesNotExist()

        // assert there is a restart button
        onNodeWithText(RESTART_GAME_BUTTON_TEXT).assertIsDisplayed()
    }

    @Test
    fun whenAppOpensShouldShowHomeRoute(): Unit = with(composeRule) {
        setContent { App(gameUseCase = { gameUseCase }) }
        onNodeWithText(PLAY_WITH_A_FRIEND_BUTTON_TEXT).assertIsDisplayed()
    }
}
