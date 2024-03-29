package me.nasrabadiam.tictactoe

import androidx.compose.ui.test.junit4.createComposeRule
import me.nasrabadiam.tictactoe.game.GameUseCase
import me.nasrabadiam.tictactoe.game.model.Player
import org.junit.Rule
import org.junit.Test

class GameClickTests {

    @get:Rule
    val composeRule = createComposeRule()

    private val gameUseCase = GameUseCase()
    private val isExpandedScreen = false

    @Test
    fun whenClickOnCellsShouldDrawItem(): Unit = with(composeRule) {
        setContent { MainScreen(gameUseCase, isExpandedScreen) }

        clickOnCell(0, 0)

        assertPlayersCountEquals(Player.X.toString(), 1)
    }

    @Test
    fun whenClickOnSecondCellShouldChangeTurn(): Unit = with(composeRule) {
        setContent { MainScreen(gameUseCase, isExpandedScreen) }

        clickOnCell(0, 0)
        clickOnCell(1, 1)

        assertPlayersCountEquals(Player.X.toString(), 1)
        assertPlayersCountEquals(Player.O.toString(), 1)
    }

    @Test
    fun whenClickOnCellThatClickedBeforeShouldDoNothing(): Unit = with(composeRule) {
        setContent { MainScreen(gameUseCase, isExpandedScreen) }

        clickOnCell(0, 0)

        assertPlayersCountEquals(Player.X.toString(), 1)

        clickOnCell(0, 0)

        assertPlayersCountEquals(Player.X.toString(), 1)
    }
}