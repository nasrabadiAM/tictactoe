package me.nasrabadiam.tictactoe

import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import me.nasrabadiam.tictactoe.game.GameUseCase
import me.nasrabadiam.tictactoe.game.Players
import org.junit.Test

class GameUseCaseShould {

    private val useCase = GameUseCase()

    @Test
    fun updateItemValueWhenClicked() = runTest {
        val index = 0
        useCase.onCellClicked(index)
        val clickedItem = useCase.cells.first()[index]
        assertEquals(Players.X.toString(), clickedItem.value)
    }

    @Test
    fun changePlayerTurnItemValueWhenClicked() = runTest {
        val index = 0
        useCase.onCellClicked(index)
        assertEquals(Players.O.toString(), useCase.currentPlayer)
    }

    @Test
    fun doNothingWhenACellWithValueClicked() = runTest {
        val index = 0
        repeat(2) {
            useCase.onCellClicked(index)
        }
        val clickedCell = useCase.cells.first()[index]
        assertEquals(Players.X.toString(), clickedCell.value)
        assertEquals(Players.O.toString(), useCase.currentPlayer)
    }

    @Test
    fun update8thItemValueOfCellsWhenClicked() = runTest {
        val index = 8
        useCase.onCellClicked(index)
        val clickedItem = useCase.cells.first()[index]
        assertEquals(Players.X.toString(), clickedItem.value)
    }

    @Test
    fun changePlayerTurnItemValueWhenClickedAndStarterIsO() = runTest {
        val index = 0
        val useCase = GameUseCase(starterPlayer = Players.O)
        useCase.onCellClicked(index)
        assertEquals(Players.X.toString(), useCase.currentPlayer)
    }

    @Test
    fun doNothingWhenCellsIsEmpty() = runTest {
        val index = 0
        val useCase = GameUseCase(boardSize = 0)
        useCase.onCellClicked(index)
        val cells = useCase.cells.first()
        assertTrue(cells.isEmpty())
        assertEquals(Players.X.toString(), useCase.currentPlayer)
    }
}