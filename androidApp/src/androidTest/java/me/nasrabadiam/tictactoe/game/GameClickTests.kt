package me.nasrabadiam.tictactoe.game

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.lifecycle.SavedStateHandle
import me.nasrabadiam.tictactoe.GameWindowSizeClass.COMPACT
import me.nasrabadiam.tictactoe.assertPlayersCountEquals
import me.nasrabadiam.tictactoe.clickOnCell
import me.nasrabadiam.tictactoe.di.component.createGameComponents
import me.nasrabadiam.tictactoe.game.model.Game
import me.nasrabadiam.tictactoe.game.model.GameMode
import me.nasrabadiam.tictactoe.game.model.Player
import me.nasrabadiam.tictactoe.game.ui.GameScreen
import me.nasrabadiam.tictactoe.game.ui.GameViewModel
import org.junit.Rule
import org.junit.Test

class GameClickTests {

    @get:Rule
    val composeRule = createComposeRule()

    private val gameUseCase: GameUseCase = createGameComponents().gameUseCase
    private val gameViewModel = GameViewModel(
        gameUseCase,
        SavedStateHandle(),
        Game(GameMode.PlayWithFriend),
    )
    private val windowClass = COMPACT

    @Test
    fun whenClickOnCellsShouldDrawItem(): Unit = with(composeRule) {
        setContent { GameScreen(gameViewModel, windowClass) }

        clickOnCell(0, 0)

        assertPlayersCountEquals(Player.X.toString(), 1)
    }

    @Test
    fun whenClickOnSecondCellShouldChangeTurn(): Unit = with(composeRule) {
        setContent { GameScreen(gameViewModel, windowClass) }

        clickOnCell(0, 0)
        clickOnCell(1, 1)

        assertPlayersCountEquals(Player.X.toString(), 1)
        assertPlayersCountEquals(Player.O.toString(), 1)
    }

    @Test
    fun whenClickOnCellThatClickedBeforeShouldDoNothing(): Unit = with(composeRule) {
        setContent { GameScreen(gameViewModel, windowClass) }

        clickOnCell(0, 0)

        assertPlayersCountEquals(Player.X.toString(), 1)

        clickOnCell(0, 0, Player.X)

        assertPlayersCountEquals(Player.X.toString(), 1)
    }
}