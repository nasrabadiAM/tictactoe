package me.nasrabadiam.tictactoe.game

import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.lifecycle.SavedStateHandle
import me.nasrabadiam.tictactoe.assertPlayersCountEquals
import me.nasrabadiam.tictactoe.clickOnCell
import me.nasrabadiam.tictactoe.game.model.Player
import me.nasrabadiam.tictactoe.game.ui.GameScreen
import me.nasrabadiam.tictactoe.game.ui.GameViewModel
import me.nasrabadiam.tictactoe.ui.getWindowSizeClass
import org.junit.Rule
import org.junit.Test

class GameClickTests {

    @get:Rule
    val composeRule = createComposeRule()

    private val gameUseCase = GameUseCase()
    private val gameViewModel = GameViewModel(gameUseCase, SavedStateHandle())

    private fun ComposeContentTestRule.setContent(
        viewModel: GameViewModel = gameViewModel
    ) {
        setContent {
            val windowSizeClass = getWindowSizeClass()
            GameScreen(viewModel, windowSizeClass)
        }
    }

    @Test
    fun whenClickOnCellsShouldDrawItem(): Unit = with(composeRule) {
        setContent()

        clickOnCell(0, 0)

        assertPlayersCountEquals(Player.X.toString(), 1)
    }

    @Test
    fun whenClickOnSecondCellShouldChangeTurn(): Unit = with(composeRule) {
        setContent()

        clickOnCell(0, 0)
        clickOnCell(1, 1)

        assertPlayersCountEquals(Player.X.toString(), 1)
        assertPlayersCountEquals(Player.O.toString(), 1)
    }

    @Test
    fun whenClickOnCellThatClickedBeforeShouldDoNothing(): Unit = with(composeRule) {
        setContent()

        clickOnCell(0, 0)

        assertPlayersCountEquals(Player.X.toString(), 1)

        clickOnCell(0, 0, Player.X)

        assertPlayersCountEquals(Player.X.toString(), 1)
    }
}