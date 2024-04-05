package me.nasrabadiam.tictactoe

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.lifecycle.SavedStateHandle
import me.nasrabadiam.tictactoe.GameWindowSizeClass.COMPACT
import me.nasrabadiam.tictactoe.game.GameUseCase
import me.nasrabadiam.tictactoe.game.model.Player
import org.junit.Rule
import org.junit.Test

class GameClickTests {

    @get:Rule
    val composeRule = createComposeRule()

    private val gameUseCase = GameUseCase()
    private val gameViewModel = GameViewModel(gameUseCase, SavedStateHandle())
    private val windowClass = COMPACT

    @Test
    fun whenClickOnCellsShouldDrawItem(): Unit = with(composeRule) {
        setContent { MainScreen(gameViewModel, windowClass) }

        clickOnCell(0, 0)

        assertPlayersCountEquals(Player.X.toString(), 1)
    }

    @Test
    fun whenClickOnSecondCellShouldChangeTurn(): Unit = with(composeRule) {
        setContent { MainScreen(gameViewModel, windowClass) }

        clickOnCell(0, 0)
        clickOnCell(1, 1)

        assertPlayersCountEquals(Player.X.toString(), 1)
        assertPlayersCountEquals(Player.O.toString(), 1)
    }

    @Test
    fun whenClickOnCellThatClickedBeforeShouldDoNothing(): Unit = with(composeRule) {
        setContent { MainScreen(gameViewModel, windowClass) }

        clickOnCell(0, 0)

        assertPlayersCountEquals(Player.X.toString(), 1)

        clickOnCell(0, 0)

        assertPlayersCountEquals(Player.X.toString(), 1)
    }
}