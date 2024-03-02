package me.nasrabadiam.tictactoe

import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import me.nasrabadiam.tictactoe.game.GameUseCase
import me.nasrabadiam.tictactoe.game.Players
import me.nasrabadiam.tictactoe.game.utlis.getCellIndex
import me.nasrabadiam.tictactoe.utils.launchInTest
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

    @Test
    fun emitGameResultWhenXFillsFirstColumn() = runTest {
        val results = mutableListOf<Players?>()
        val job = this.launchInTest {
            useCase.gameResult.toList(results)
        }

        val xColumn = 0
        useCase.onCellClicked(getCellIndex(row = 0, col = xColumn)) // X
        useCase.onCellClicked(getCellIndex(row = 0, col = 1)) // O
        assertEquals(listOf<Players?>(null), results)

        useCase.onCellClicked(getCellIndex(row = 1, col = xColumn)) // X
        useCase.onCellClicked(getCellIndex(row = 1, col = 1)) // O
        assertEquals(listOf<Players?>(null), results)

        useCase.onCellClicked(getCellIndex(row = 2, col = xColumn)) // X
        assertEquals(listOf(null, Players.X), results)

        job.cancel()
    }

    @Test
    fun emitGameResultWhenXFillsSecondColumn() = runTest {
        val results = mutableListOf<Players?>()
        val job = this.launchInTest {
            useCase.gameResult.toList(results)
        }
        val xColumn = 1

        useCase.onCellClicked(getCellIndex(row = 0, col = xColumn)) // X
        useCase.onCellClicked(getCellIndex(row = 0, col = 0)) // O
        assertEquals(listOf<Players?>(null), results)

        useCase.onCellClicked(getCellIndex(row = 1, col = xColumn)) // X
        useCase.onCellClicked(getCellIndex(row = 1, col = 0)) // O
        assertEquals(listOf<Players?>(null), results)

        useCase.onCellClicked(getCellIndex(row = 2, col = xColumn)) // X
        assertEquals(listOf(null, Players.X), results)

        job.cancel()
    }

    @Test
    fun emitGameResultWhenXFillsThirdColumn() = runTest {
        val results = mutableListOf<Players?>()
        val job = this.launchInTest {
            useCase.gameResult.toList(results)
        }

        val xColumn = 2
        useCase.onCellClicked(getCellIndex(row = 0, col = xColumn)) // X
        useCase.onCellClicked(getCellIndex(row = 0, col = 0)) // O
        assertEquals(listOf<Players?>(null), results)

        useCase.onCellClicked(getCellIndex(row = 1, col = xColumn)) // X
        useCase.onCellClicked(getCellIndex(row = 1, col = 0)) // O
        assertEquals(listOf<Players?>(null), results)

        useCase.onCellClicked(getCellIndex(row = 2, col = xColumn)) // X
        assertEquals(listOf(null, Players.X), results)

        job.cancel()
    }

    @Test
    fun emitGameResultWhenOFillsFirstColumn() = runTest {
        val results = mutableListOf<Players?>()
        val job = this.launchInTest {
            useCase.gameResult.toList(results)
        }

        useCase.onCellClicked(getCellIndex(row = 0, col = 1)) // X
        useCase.onCellClicked(getCellIndex(row = 0, col = 0)) // O
        assertEquals(listOf<Players?>(null), results)

        useCase.onCellClicked(getCellIndex(row = 0, col = 2)) // X
        useCase.onCellClicked(getCellIndex(row = 1, col = 0)) // O
        assertEquals(listOf<Players?>(null), results)

        useCase.onCellClicked(getCellIndex(row = 2, col = 2)) // X
        useCase.onCellClicked(getCellIndex(row = 2, col = 0)) // O
        assertEquals(listOf(null, Players.O), results)

        job.cancel()
    }
}