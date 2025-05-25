package me.nasrabadiam.tictactoe.ui.game

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.runComposeUiTest
import androidx.lifecycle.SavedStateHandle
import me.nasrabadiam.tictactoe.GameWindowSizeClass.COMPACT
import me.nasrabadiam.tictactoe.game.GameUseCase
import me.nasrabadiam.tictactoe.game.model.Player
import me.nasrabadiam.tictactoe.game.ui.GameScreenRoute
import me.nasrabadiam.tictactoe.game.ui.GameViewModel
import me.nasrabadiam.tictactoe.ui.assertPlayersCountEquals
import me.nasrabadiam.tictactoe.ui.clickOnCell
import kotlin.test.Test

@OptIn(ExperimentalTestApi::class)
class GameClickTests {

    private val gameUseCase = GameUseCase()
    private val gameViewModel = GameViewModel(gameUseCase, SavedStateHandle())
    private val windowClass = COMPACT

    @Test
    fun whenClickOnCellsShouldDrawItem() = runComposeUiTest {
        setContent { GameScreenRoute(gameViewModel, windowClass) }

        clickOnCell(0, 0)

        assertPlayersCountEquals("X", 1)
    }

    @Test
    fun whenClickOnSecondCellShouldChangeTurn() = runComposeUiTest {
        setContent { GameScreenRoute(gameViewModel, windowClass) }

        clickOnCell(0, 0)
        clickOnCell(1, 1)

        assertPlayersCountEquals(Player.X.toString(), 1)
        assertPlayersCountEquals(Player.O.toString(), 1)
    }

    @Test
    fun whenClickOnCellThatClickedBeforeShouldDoNothing() = runComposeUiTest {
        setContent { GameScreenRoute(gameViewModel, windowClass) }

        clickOnCell(0, 0)

        assertPlayersCountEquals(Player.X.toString(), 1)

        clickOnCell(0, 0, Player.X)

        assertPlayersCountEquals(Player.X.toString(), 1)
    }
}