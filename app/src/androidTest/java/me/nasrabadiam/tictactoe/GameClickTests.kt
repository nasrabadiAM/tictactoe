package me.nasrabadiam.tictactoe

import androidx.compose.ui.test.junit4.createComposeRule
import me.nasrabadiam.tictactoe.game.GameUseCase
import me.nasrabadiam.tictactoe.game.Player
import org.junit.Rule
import org.junit.Test

class GameClickTests {

    @get:Rule
    val composeRule = createComposeRule()

    private val gameUseCase = GameUseCase()

    @Test
    fun whenClickOnCellsShouldDrawItem(): Unit = with(composeRule) {
        setContent { MainScreen(gameUseCase) }

        clickOnFirstEmptyCell()

        assertCountEquals(Player.X.toString(), 1)
    }

    @Test
    fun whenClickOnSecondCellShouldChangeTurn(): Unit = with(composeRule) {
        setContent { MainScreen(gameUseCase) }

        clickOnFirstEmptyCell()
        clickOnFirstEmptyCell()

        assertCountEquals(Player.X.toString(), 1)
        assertCountEquals(Player.O.toString(), 1)
    }

    @Test
    fun whenClickOnCellThatClickedBeforeShouldDoNothing(): Unit = with(composeRule) {
        setContent { MainScreen(gameUseCase) }

        clickOnFirstEmptyCell()

        assertCountEquals(Player.X.toString(), 1)

        clickOnXCell()

        assertCountEquals(Player.X.toString(), 1)
    }
}