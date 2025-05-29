package me.nasrabadiam.tictactoe

import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import me.nasrabadiam.tictactoe.game.GameUseCase
import me.nasrabadiam.tictactoe.game.GameUseCase.Orientation.COLUMN
import me.nasrabadiam.tictactoe.game.GameUseCase.Orientation.CROSS
import me.nasrabadiam.tictactoe.game.GameUseCase.Orientation.ROW
import me.nasrabadiam.tictactoe.game.model.AI_MOVE_DELAY_IN_MILLIS
import me.nasrabadiam.tictactoe.game.model.Cell
import me.nasrabadiam.tictactoe.game.model.GameMode
import me.nasrabadiam.tictactoe.game.model.GameResult
import me.nasrabadiam.tictactoe.game.model.GameResult.Draw
import me.nasrabadiam.tictactoe.game.model.GameResult.EndWithWinner
import me.nasrabadiam.tictactoe.game.model.Player
import me.nasrabadiam.tictactoe.game.model.utlis.getCellIndex
import me.nasrabadiam.tictactoe.game.model.utlis.listOfEmptyCells
import me.nasrabadiam.tictactoe.game.ui.GameState
import me.nasrabadiam.tictactoe.game.ui.ScoresState
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class GameUseCaseShould {

    private val useCase = GameUseCase()

    @Test
    fun updateItemValueWhenClicked() = runTest {
        val index = 0
        useCase.clickOnCell(index)
        val clickedItem = useCase.cells.value[index]
        assertEquals(Player.X, clickedItem.value)
    }

    @Test
    fun changePlayerTurnItemValueWhenClicked() = runTest {
        val index = 0
        useCase.clickOnCell(index)
        assertEquals(Player.O, useCase.currentPlayer.value)
    }

    @Test
    fun doNothingWhenACellWithValueClicked() = runTest {
        val index = 0
        repeat(2) {
            useCase.clickOnCell(index)
        }
        val clickedCell = useCase.cells.value[index]
        assertEquals(Player.X, clickedCell.value)
        assertEquals(Player.O, useCase.currentPlayer.value)
    }

    @Test
    fun update8thItemValueOfCellsWhenClicked() = runTest {
        val index = 8
        useCase.clickOnCell(index)
        val clickedItem = useCase.cells.value[index]
        assertEquals(Player.X, clickedItem.value)
    }

    @Test
    fun changePlayerTurnItemValueWhenClickedAndStarterIsO() = runTest {
        val index = 0
        val useCase = GameUseCase(starterPlayer = Player.O)
        useCase.clickOnCell(index)
        assertEquals(Player.X, useCase.currentPlayer.value)
    }

    @Test
    fun doNothingWhenCellsIsEmpty() = runTest {
        val index = 0
        val useCase = GameUseCase(boardSize = 0)
        useCase.clickOnCell(index)
        val cells = useCase.cells.value
        assertTrue(
            cells.isEmpty(),
            "Cells must be empty, but it's not.",
        )
        assertEquals(Player.X, useCase.currentPlayer.value)
    }

    /**
     * X Column tests
     */
    @Test
    fun emitGameResultWhenXFillsFirstColumn() = runTest {
        val xColumn = 0
        useCase.clickOnCell(getCellIndex(row = 0, col = xColumn)) // X
        useCase.clickOnCell(getCellIndex(row = 0, col = 1)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.clickOnCell(getCellIndex(row = 1, col = xColumn)) // X
        useCase.clickOnCell(getCellIndex(row = 1, col = 1)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.clickOnCell(getCellIndex(row = 2, col = xColumn)) // X
        assertEquals(
            EndWithWinner(
                player = Player.X,
                winningOrientation = COLUMN,
                winningIndex = 0
            ), useCase.gameResult.value
        )
    }

    @Test
    fun emitGameResultWhenXFillsSecondColumn() = runTest {
        val xColumn = 1
        useCase.clickOnCell(getCellIndex(row = 0, col = xColumn)) // X
        useCase.clickOnCell(getCellIndex(row = 0, col = 0)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.clickOnCell(getCellIndex(row = 1, col = xColumn)) // X
        useCase.clickOnCell(getCellIndex(row = 1, col = 0)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.clickOnCell(getCellIndex(row = 2, col = xColumn)) // X
        assertEquals(
            EndWithWinner(
                player = Player.X,
                winningOrientation = COLUMN,
                winningIndex = 1
            ), useCase.gameResult.value
        )
    }

    @Test
    fun emitGameResultWhenXFillsThirdColumn() = runTest {
        val xColumn = 2
        useCase.clickOnCell(getCellIndex(row = 0, col = xColumn)) // X
        useCase.clickOnCell(getCellIndex(row = 0, col = 0)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.clickOnCell(getCellIndex(row = 1, col = xColumn)) // X
        useCase.clickOnCell(getCellIndex(row = 1, col = 0)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.clickOnCell(getCellIndex(row = 2, col = xColumn)) // X
        assertEquals(
            EndWithWinner(
                player = Player.X,
                winningOrientation = COLUMN,
                winningIndex = 2
            ), useCase.gameResult.value
        )
    }

    /**
     * X Row tests
     */
    @Test
    fun emitGameResultWhenXFillsFirstRow() = runTest {
        val xRow = 0
        useCase.clickOnCell(getCellIndex(row = xRow, col = 0)) // X
        useCase.clickOnCell(getCellIndex(row = 1, col = 0)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.clickOnCell(getCellIndex(row = xRow, col = 1)) // X
        useCase.clickOnCell(getCellIndex(row = 1, col = 1)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.clickOnCell(getCellIndex(row = xRow, col = 2)) // X
        assertEquals(
            EndWithWinner(
                player = Player.X,
                winningOrientation = ROW,
                winningIndex = 0
            ), useCase.gameResult.value
        )
    }

    @Test
    fun emitGameResultWhenXFillsSecondRow() = runTest {
        val xRow = 1
        useCase.clickOnCell(getCellIndex(row = xRow, col = 0)) // X
        useCase.clickOnCell(getCellIndex(row = 2, col = 0)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.clickOnCell(getCellIndex(row = xRow, col = 1)) // X
        useCase.clickOnCell(getCellIndex(row = 0, col = 0)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.clickOnCell(getCellIndex(row = xRow, col = 2)) // X
        assertEquals(
            EndWithWinner(
                player = Player.X,
                winningOrientation = ROW,
                winningIndex = 1
            ), useCase.gameResult.value
        )
    }

    @Test
    fun emitGameResultWhenXFillsThirdRow() = runTest {
        val xRow = 2
        useCase.clickOnCell(getCellIndex(row = xRow, col = 0)) // X
        useCase.clickOnCell(getCellIndex(row = 1, col = 0)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.clickOnCell(getCellIndex(row = xRow, col = 1)) // X
        useCase.clickOnCell(getCellIndex(row = 1, col = 1)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.clickOnCell(getCellIndex(row = xRow, col = 2)) // X
        assertEquals(
            EndWithWinner(
                player = Player.X,
                winningOrientation = ROW,
                winningIndex = 2
            ), useCase.gameResult.value
        )
    }

    /**
     * X Cross tests
     */
    @Test
    fun emitGameResultWhenXFillsLeftToRightCross() = runTest {
        useCase.clickOnCell(getCellIndex(row = 0, col = 0)) // X
        useCase.clickOnCell(getCellIndex(row = 0, col = 2)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.clickOnCell(getCellIndex(row = 1, col = 1)) // X
        useCase.clickOnCell(getCellIndex(row = 1, col = 2)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.clickOnCell(getCellIndex(row = 2, col = 2)) // X
        assertEquals(
            EndWithWinner(
                player = Player.X,
                winningOrientation = CROSS,
                winningIndex = 0
            ), useCase.gameResult.value
        )
    }

    @Test
    fun emitGameResultWhenXFillsRightToLeftCross() = runTest {
        useCase.clickOnCell(getCellIndex(row = 0, col = 2)) // X
        useCase.clickOnCell(getCellIndex(row = 1, col = 2)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.clickOnCell(getCellIndex(row = 1, col = 1)) // X
        useCase.clickOnCell(getCellIndex(row = 1, col = 0)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.clickOnCell(getCellIndex(row = 2, col = 0)) // X
        assertEquals(
            EndWithWinner(
                player = Player.X,
                winningOrientation = CROSS,
                winningIndex = 1
            ), useCase.gameResult.value
        )
    }

    /**
     * O Column tests
     */
    @Test
    fun emitGameResultWhenOFillsFirstColumn() = runTest {
        val oColumn = 0
        useCase.clickOnCell(getCellIndex(row = 2, col = 1)) // X
        useCase.clickOnCell(getCellIndex(row = 0, col = oColumn)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.clickOnCell(getCellIndex(row = 2, col = 2)) // X
        useCase.clickOnCell(getCellIndex(row = 1, col = oColumn)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.clickOnCell(getCellIndex(row = 1, col = 1)) // X
        useCase.clickOnCell(getCellIndex(row = 2, col = oColumn)) // O
        assertEquals(
            EndWithWinner(
                player = Player.O,
                winningOrientation = COLUMN,
                winningIndex = 0
            ), useCase.gameResult.value
        )
    }

    @Test
    fun emitGameResultWhenOFillsSecondColumn() = runTest {
        val oColumn = 1
        useCase.clickOnCell(getCellIndex(row = 0, col = 0)) // X
        useCase.clickOnCell(getCellIndex(row = 0, col = oColumn)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.clickOnCell(getCellIndex(row = 1, col = 0)) // X
        useCase.clickOnCell(getCellIndex(row = 1, col = oColumn)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.clickOnCell(getCellIndex(row = 2, col = 2)) // X
        useCase.clickOnCell(getCellIndex(row = 2, col = oColumn)) // X
        assertEquals(
            EndWithWinner(
                player = Player.O,
                winningOrientation = COLUMN,
                winningIndex = 1
            ), useCase.gameResult.value
        )
    }

    @Test
    fun emitGameResultWhenOFillsThirdColumn() = runTest {
        val oColumn = 2
        useCase.clickOnCell(getCellIndex(row = 0, col = 0)) // X
        useCase.clickOnCell(getCellIndex(row = 0, col = oColumn)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.clickOnCell(getCellIndex(row = 1, col = 0)) // X
        useCase.clickOnCell(getCellIndex(row = 1, col = oColumn)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.clickOnCell(getCellIndex(row = 2, col = 1)) // X
        useCase.clickOnCell(getCellIndex(row = 2, col = oColumn)) // X
        assertEquals(
            EndWithWinner(
                player = Player.O,
                winningOrientation = COLUMN,
                winningIndex = 2
            ), useCase.gameResult.value
        )
    }

    /**
     * O Row tests
     */
    @Test
    fun emitGameResultWhenOFillsFirstRow() = runTest {
        val oRow = 0
        useCase.clickOnCell(getCellIndex(row = 1, col = 0)) // X
        useCase.clickOnCell(getCellIndex(row = oRow, col = 0)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.clickOnCell(getCellIndex(row = 1, col = 1)) // X
        useCase.clickOnCell(getCellIndex(row = oRow, col = 1)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.clickOnCell(getCellIndex(row = 2, col = 2)) // X
        useCase.clickOnCell(getCellIndex(row = oRow, col = 2)) // O
        assertEquals(
            EndWithWinner(
                player = Player.O,
                winningOrientation = ROW,
                winningIndex = 0
            ), useCase.gameResult.value
        )
    }

    @Test
    fun emitGameResultWhenOFillsSecondRow() = runTest {
        val oRow = 1
        useCase.clickOnCell(getCellIndex(row = 2, col = 0)) // X
        useCase.clickOnCell(getCellIndex(row = oRow, col = 0)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.clickOnCell(getCellIndex(row = 2, col = 1)) // X
        useCase.clickOnCell(getCellIndex(row = oRow, col = 1)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.clickOnCell(getCellIndex(row = 0, col = 0)) // X
        useCase.clickOnCell(getCellIndex(row = oRow, col = 2)) // O
        assertEquals(
            EndWithWinner(
                player = Player.O,
                winningOrientation = ROW,
                winningIndex = 1
            ), useCase.gameResult.value
        )
    }

    @Test
    fun emitGameResultWhenOFillsThirdRow() = runTest {
        val oRow = 2
        useCase.clickOnCell(getCellIndex(row = 1, col = 0)) // X
        useCase.clickOnCell(getCellIndex(row = oRow, col = 0)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.clickOnCell(getCellIndex(row = 1, col = 1)) // X
        useCase.clickOnCell(getCellIndex(row = oRow, col = 1)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.clickOnCell(getCellIndex(row = 0, col = 2)) // X
        useCase.clickOnCell(getCellIndex(row = oRow, col = 2)) // O
        assertEquals(
            EndWithWinner(
                player = Player.O,
                winningOrientation = ROW,
                winningIndex = 2
            ), useCase.gameResult.value
        )
    }

    /**
     * O Cross tests
     */
    @Test
    fun emitGameResultWhenOFillsLeftToRightCross() = runTest {
        useCase.clickOnCell(getCellIndex(row = 0, col = 2)) // X
        useCase.clickOnCell(getCellIndex(row = 0, col = 0)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.clickOnCell(getCellIndex(row = 1, col = 2)) // X
        useCase.clickOnCell(getCellIndex(row = 1, col = 1)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.clickOnCell(getCellIndex(row = 2, col = 0)) // X
        useCase.clickOnCell(getCellIndex(row = 2, col = 2)) // O
        assertEquals(
            EndWithWinner(
                player = Player.O,
                winningOrientation = CROSS,
                winningIndex = 0
            ), useCase.gameResult.value
        )
    }

    @Test
    fun emitGameResultWhenOFillsRightToLeftCross() = runTest {
        useCase.clickOnCell(getCellIndex(row = 1, col = 2)) // X
        useCase.clickOnCell(getCellIndex(row = 0, col = 2)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.clickOnCell(getCellIndex(row = 1, col = 0)) // X
        useCase.clickOnCell(getCellIndex(row = 1, col = 1)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.clickOnCell(getCellIndex(row = 2, col = 1)) // X
        useCase.clickOnCell(getCellIndex(row = 2, col = 0)) // O
        assertEquals(
            EndWithWinner(
                player = Player.O,
                winningOrientation = CROSS,
                winningIndex = 1
            ), useCase.gameResult.value
        )
    }

    @Test
    fun emitDrawGameResultWhenNoOneWins() = runTest {
        useCase.clickOnCell(getCellIndex(col = 0, row = 0)) // X
        useCase.clickOnCell(getCellIndex(col = 2, row = 0)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.clickOnCell(getCellIndex(col = 1, row = 1)) // X
        useCase.clickOnCell(getCellIndex(col = 0, row = 1)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.clickOnCell(getCellIndex(col = 0, row = 2)) // X
        useCase.clickOnCell(getCellIndex(col = 2, row = 2)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.clickOnCell(getCellIndex(col = 2, row = 1)) // X
        useCase.clickOnCell(getCellIndex(col = 1, row = 0)) // O
        assertEquals(null, useCase.gameResult.value)

        useCase.clickOnCell(getCellIndex(col = 1, row = 2)) // X
        assertEquals(Draw, useCase.gameResult.value)
    }

    @Test
    fun resetGameCellsAndScoresWhenClickedOnRestartButton() = runTest {
        useCase.clickOnCell(getCellIndex(col = 1, row = 2)) // X
        useCase.clickOnCell(getCellIndex(col = 0, row = 0)) // O

        useCase.restartGame()

        val cells = useCase.cells.value
        assertTrue(
            cells.all { it.value == null },
            "All cells should be empty after restart but they aren't," +
                " non empty cells=${cells.filter { it.value != null }}"

        )

        assertEquals(0, useCase.xScore.value)
        assertEquals(0, useCase.drawCount.value)
        assertEquals(0, useCase.oScore.value)
    }

    @Test
    fun resetGameResultWhenClickedOnRestartButton() = runTest {
        useCase.clickOnCell(getCellIndex(col = 0, row = 0)) // X
        useCase.clickOnCell(getCellIndex(col = 2, row = 0)) // O

        useCase.clickOnCell(getCellIndex(col = 1, row = 1)) // X
        useCase.clickOnCell(getCellIndex(col = 0, row = 1)) // O

        useCase.clickOnCell(getCellIndex(col = 0, row = 2)) // X
        useCase.clickOnCell(getCellIndex(col = 2, row = 2)) // O

        useCase.clickOnCell(getCellIndex(col = 2, row = 1)) // X
        useCase.clickOnCell(getCellIndex(col = 1, row = 0)) // O

        useCase.clickOnCell(getCellIndex(col = 1, row = 2)) // X

        useCase.restartGame()

        val gameResult = useCase.gameResult.value
        assertTrue(
            gameResult == null,
            "Game Result should by empty when game has been reset," +
                " but it is -> gameResult=$gameResult",
        )
    }

    @Test
    fun resetGameCurrentPlayerToStarterPlayerWhenClickedOnRestartButton() = runTest {
        // Starter player here is X
        useCase.clickOnCell(getCellIndex(col = 0, row = 0)) // X
        // Current player here is O

        useCase.restartGame()

        val currentPlayer = useCase.currentPlayer.value
        assertTrue(
            currentPlayer == Player.X,
            "Current Player must be restart to starter player, " +
                "when we restart the game but it is -> currentPlayer=$currentPlayer",
        )
    }

    @Test
    fun emitXScoreWhenItWins() = runTest {
        val xColumn = 1
        useCase.clickOnCell(getCellIndex(row = 0, col = xColumn)) // X
        useCase.clickOnCell(getCellIndex(row = 0, col = 0)) // O

        useCase.clickOnCell(getCellIndex(row = 1, col = xColumn)) // X
        useCase.clickOnCell(getCellIndex(row = 1, col = 0)) // O

        useCase.clickOnCell(getCellIndex(row = 2, col = xColumn)) // X wins
        assertEquals(1, useCase.xScore.value)
    }

    @Test
    fun emitOScoreWhenItWins() = runTest {
        useCase.clickOnCell(getCellIndex(row = 0, col = 2)) // X
        useCase.clickOnCell(getCellIndex(row = 0, col = 0)) // O

        useCase.clickOnCell(getCellIndex(row = 1, col = 2)) // X
        useCase.clickOnCell(getCellIndex(row = 1, col = 1)) // O

        useCase.clickOnCell(getCellIndex(row = 2, col = 0)) // X
        useCase.clickOnCell(getCellIndex(row = 2, col = 2)) // O wins

        assertEquals(1, useCase.oScore.value)
    }

    @Test
    fun emitDrawCountWhenItDraws() = runTest {

        useCase.clickOnCell(getCellIndex(col = 0, row = 0)) // X
        useCase.clickOnCell(getCellIndex(col = 2, row = 0)) // O

        useCase.clickOnCell(getCellIndex(col = 1, row = 1)) // X
        useCase.clickOnCell(getCellIndex(col = 0, row = 1)) // O

        useCase.clickOnCell(getCellIndex(col = 0, row = 2)) // X
        useCase.clickOnCell(getCellIndex(col = 2, row = 2)) // O

        useCase.clickOnCell(getCellIndex(col = 2, row = 1)) // X
        useCase.clickOnCell(getCellIndex(col = 1, row = 0)) // O

        useCase.clickOnCell(getCellIndex(col = 1, row = 2)) // X -> Draw

        assertEquals(1, useCase.drawCount.value)
    }

    @Test
    fun resetGameCellsWhenClickedOnReplayButton() = runTest {
        useCase.clickOnCell(getCellIndex(col = 0, row = 0)) // X
        useCase.clickOnCell(getCellIndex(col = 2, row = 0)) // O

        useCase.clickOnCell(getCellIndex(col = 1, row = 1)) // X
        useCase.clickOnCell(getCellIndex(col = 0, row = 1)) // O

        useCase.clickOnCell(getCellIndex(col = 0, row = 2)) // X
        useCase.clickOnCell(getCellIndex(col = 2, row = 2)) // O

        useCase.clickOnCell(getCellIndex(col = 2, row = 1)) // X
        useCase.clickOnCell(getCellIndex(col = 1, row = 0)) // O

        useCase.clickOnCell(getCellIndex(col = 1, row = 2)) // X -> Draw

        useCase.replayGame()

        val cells = useCase.cells.value
        assertTrue(
            cells.all { it.value == null },
            "All cells should be empty after restart but they aren't," +
                " non empty cells=${cells.filter { it.value != null }}",
        )

        assertEquals(0, useCase.xScore.value)
        assertEquals(1, useCase.drawCount.value)
        assertEquals(0, useCase.oScore.value)
    }

    // //////
    // Turn change Tests
    // //////
    @Test
    fun changeTheCurrentPlayerToOWhenOWins() = runTest {
        val oColumn = 1
        useCase.clickOnCell(getCellIndex(row = 0, col = 0)) // X
        useCase.clickOnCell(getCellIndex(row = 0, col = oColumn)) // O

        useCase.clickOnCell(getCellIndex(row = 1, col = 0)) // X
        useCase.clickOnCell(getCellIndex(row = 1, col = oColumn)) // O

        useCase.clickOnCell(getCellIndex(row = 2, col = 2)) // X
        useCase.clickOnCell(getCellIndex(row = 2, col = oColumn)) // O wins

        useCase.replayGame()

        assertEquals(Player.O, useCase.currentPlayer.value)
    }

    @Test
    fun changeTheCurrentPlayerToXWhenXWins() = runTest {
        val oColumn = 1
        useCase.clickOnCell(getCellIndex(row = 0, col = oColumn)) // X
        useCase.clickOnCell(getCellIndex(row = 0, col = 0)) // O

        useCase.clickOnCell(getCellIndex(row = 1, col = oColumn)) // X
        useCase.clickOnCell(getCellIndex(row = 1, col = 0)) // O

        useCase.clickOnCell(getCellIndex(row = 2, col = oColumn)) // X wins

        useCase.replayGame()

        assertEquals(Player.X, useCase.currentPlayer.value)
    }

    @Test
    fun changeTheCurrentPlayerToOWhenGameWithXStarterDraw() = runTest {
        useCase.clickOnCell(getCellIndex(col = 0, row = 0)) // X
        useCase.clickOnCell(getCellIndex(col = 2, row = 0)) // O

        useCase.clickOnCell(getCellIndex(col = 1, row = 1)) // X
        useCase.clickOnCell(getCellIndex(col = 0, row = 1)) // O

        useCase.clickOnCell(getCellIndex(col = 0, row = 2)) // X
        useCase.clickOnCell(getCellIndex(col = 2, row = 2)) // O

        useCase.clickOnCell(getCellIndex(col = 2, row = 1)) // X
        useCase.clickOnCell(getCellIndex(col = 1, row = 0)) // O

        useCase.clickOnCell(getCellIndex(col = 1, row = 2)) // X -> Draw

        useCase.replayGame()

        assertEquals(Player.O, useCase.currentPlayer.value)
    }

    @Test
    fun changeTheCurrentPlayerToXWhenGameWithOStarterDraw() = runTest {
        val oColumn = 1
        useCase.clickOnCell(getCellIndex(row = 0, col = 0)) // X
        useCase.clickOnCell(getCellIndex(row = 0, col = oColumn)) // O

        useCase.clickOnCell(getCellIndex(row = 1, col = 0)) // X
        useCase.clickOnCell(getCellIndex(row = 1, col = oColumn)) // O

        useCase.clickOnCell(getCellIndex(row = 2, col = 2)) // X
        useCase.clickOnCell(getCellIndex(row = 2, col = oColumn)) // O wins

        useCase.replayGame()

        useCase.clickOnCell(getCellIndex(col = 2, row = 0)) // O
        useCase.clickOnCell(getCellIndex(col = 0, row = 0)) // X

        useCase.clickOnCell(getCellIndex(col = 0, row = 1)) // O
        useCase.clickOnCell(getCellIndex(col = 1, row = 1)) // X

        useCase.clickOnCell(getCellIndex(col = 2, row = 2)) // O
        useCase.clickOnCell(getCellIndex(col = 0, row = 2)) // X

        useCase.clickOnCell(getCellIndex(col = 1, row = 0)) // O
        useCase.clickOnCell(getCellIndex(col = 2, row = 1)) // X

        useCase.clickOnCell(getCellIndex(col = 1, row = 2)) // O -> Draw

        useCase.replayGame()

        assertEquals(Player.X, useCase.currentPlayer.value)
    }
    // //////
    // Turn change Tests
    // //////

    @Test
    fun restoreGameStateWhenRestoreGameCalled() = runTest {
        val cells = listOfEmptyCells().toMutableList().apply {
            this[0] = this[0].copy(value = Player.X)
        }
        val gameResult = EndWithWinner(
            player = Player.O,
            winningOrientation = CROSS,
            winningIndex = 0
        )
        val currentPlayer = Player.O

        useCase.restoreGameState(
            GameState(
                cells = cells,
                gameResult = gameResult,
                currentPlayer = currentPlayer,
                scores = ScoresState(1, 2, 3),
                gameMode = GameMode.PLAYER_VS_PLAYER
            )
        )

        assertEquals(useCase.gameResult.value, gameResult)
        assertEquals(useCase.cells.value, cells)
        assertEquals(useCase.currentPlayer.value, currentPlayer)
        assertEquals(useCase.xScore.value, 1)
        assertEquals(useCase.oScore.value, 2)
        assertEquals(useCase.drawCount.value, 3)
    }

    @Test
    fun doNothingWhenGamesEndsWinner() = runTest {
        val oColumn = 1
        useCase.clickOnCell(getCellIndex(row = 0, col = 0)) // X
        useCase.clickOnCell(getCellIndex(row = 0, col = oColumn)) // O

        useCase.clickOnCell(getCellIndex(row = 1, col = 0)) // X
        useCase.clickOnCell(getCellIndex(row = 1, col = oColumn)) // O

        useCase.clickOnCell(getCellIndex(row = 2, col = 2)) // X
        useCase.clickOnCell(getCellIndex(row = 2, col = oColumn)) // O wins

        // game ends, but we click in a new cell, and nothing should happen
        useCase.clickOnCell(getCellIndex(row = 1, col = 2)) // Cell 5 clicked but nothing happen

        assertEquals(Cell(index = 5), useCase.cells.value[5])
    }

    @Test
    fun restoreGameStateWithAIMode() = runTest {
        val cells = listOfEmptyCells().toMutableList().apply {
            this[0] = this[0].copy(value = Player.X)
            this[4] = this[4].copy(value = Player.O)
        }
        val gameState = GameState(
            cells = cells,
            gameResult = null,
            currentPlayer = Player.X,
            scores = ScoresState(0, 0, 0),
            gameMode = GameMode.PLAYER_VS_AI
        )

        useCase.restoreGameState(gameState)

        assertEquals(GameMode.PLAYER_VS_AI, useCase.gameMode.value)
        assertEquals(cells, useCase.cells.value)
        assertEquals(Player.X, useCase.currentPlayer.value)
    }

    @Test
    fun preventAIPlayerClicksInAIMode() = runTest {
        useCase.restoreGameState(
            GameState(
                cells = listOfEmptyCells(),
                gameResult = null,
                currentPlayer = Player.O,
                scores = ScoresState(0, 0, 0),
                gameMode = GameMode.PLAYER_VS_AI
            )
        )

        // Try to click when it's AI's turn (Player.O)
        useCase.clickOnCell(0)

        // Cell should remain empty since AI moves aren't allowed via click
        assertEquals(null, useCase.cells.value[0].value)
    }

    @Test
    fun allowHumanPlayerClicksInAIMode() = runTest {
        useCase.restoreGameState(
            GameState(
                cells = listOfEmptyCells(),
                gameResult = null,
                currentPlayer = Player.X,
                scores = ScoresState(0, 0, 0),
                gameMode = GameMode.PLAYER_VS_AI
            )
        )

        // Human player (X) should be able to click
        useCase.clickOnCell(0)

        assertEquals(Player.X, useCase.cells.value[0].value)
    }

    @Test
    fun aiMakesValidMoveAfterHumanMove() = runTest {
        useCase.restoreGameState(
            GameState(
                cells = listOfEmptyCells(),
                gameResult = null,
                currentPlayer = Player.X,
                scores = ScoresState(0, 0, 0),
                gameMode = GameMode.PLAYER_VS_AI
            )
        )

        // Human makes first move
        useCase.clickOnCell(0)

        // Give AI time to make its move
        delay(AI_MOVE_DELAY_IN_MILLIS + 100)

        // Check that AI made a move (some cell other than 0 should be filled with O)
        val aiMoveMade = useCase.cells.value.any { cell ->
            cell.value == Player.O && cell.index != 0
        }
        assertTrue(aiMoveMade, "AI should have made a move after human move")
    }

    @Test
    fun aiDoesNotMoveWhenGameEnds() = runTest {
        val winningCells = listOfEmptyCells().toMutableList().apply {
            this[0] = this[0].copy(value = Player.X)
            this[1] = this[1].copy(value = Player.X)
        }

        useCase.restoreGameState(
            GameState(
                cells = winningCells,
                gameResult = null,
                currentPlayer = Player.X,
                scores = ScoresState(0, 0, 0),
                gameMode = GameMode.PLAYER_VS_AI
            )
        )

        // Human wins the game
        useCase.clickOnCell(2)

        // Give time for any potential AI move
        delay(AI_MOVE_DELAY_IN_MILLIS + 100)

        // Game should be over, AI shouldn't move
        assertTrue(useCase.gameResult.value is GameResult.EndWithWinner)
        assertEquals(Player.X, (useCase.gameResult.value as GameResult.EndWithWinner).player)
    }

    @Test
    fun restartGameWithAIModeStartsCorrectly() = runTest {
        useCase.restoreGameState(
            GameState(
                cells = listOfEmptyCells().toMutableList().apply {
                    this[0] = this[0].copy(value = Player.X)
                    this[4] = this[4].copy(value = Player.O)
                },
                gameResult = null,
                currentPlayer = Player.O,
                scores = ScoresState(1, 1, 1),
                gameMode = GameMode.PLAYER_VS_AI
            )
        )

        useCase.restartGame()

        // All cells should be empty
        assertTrue(useCase.cells.value.all { it.value == null })
        // Scores should be reset
        assertEquals(0, useCase.xScore.value)
        assertEquals(0, useCase.oScore.value)
        assertEquals(0, useCase.drawCount.value)
        // Current player should be starter
        assertEquals(Player.X, useCase.currentPlayer.value)
        // Game mode should be preserved
        assertEquals(GameMode.PLAYER_VS_AI, useCase.gameMode.value)
    }

    @Test
    fun replayGameWithAIModePreservesScores() = runTest {
        useCase.restoreGameState(
            GameState(
                cells = listOfEmptyCells().toMutableList().apply {
                    this[0] = this[0].copy(value = Player.X)
                    this[1] = this[4].copy(value = Player.O)
                    this[2] = this[4].copy(value = Player.X)
                    this[3] = this[4].copy(value = Player.X)
                    this[4] = this[4].copy(value = Player.X)
                    this[5] = this[4].copy(value = Player.O)
                    this[6] = this[4].copy(value = Player.O)
                    this[7] = this[4].copy(value = Player.X)
                    this[8] = this[4].copy(value = Player.O)
                },
                gameResult = Draw,
                currentPlayer = Player.X,
                scores = ScoresState(1, 2, 3),
                gameMode = GameMode.PLAYER_VS_AI
            )
        )

        useCase.replayGame()

        // All cells should be empty
        assertTrue(useCase.cells.value.any { it.value == null })
        // Scores should be preserved
        assertEquals(1, useCase.xScore.value)
        assertEquals(2, useCase.oScore.value)
        assertEquals(3, useCase.drawCount.value)
        // Game result should be reset
        assertEquals(null, useCase.gameResult.value)
    }
}