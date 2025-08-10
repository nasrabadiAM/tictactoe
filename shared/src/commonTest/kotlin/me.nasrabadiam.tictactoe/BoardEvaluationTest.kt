package me.nasrabadiam.tictactoe

import me.nasrabadiam.tictactoe.game.model.Cell
import me.nasrabadiam.tictactoe.game.model.GameResult
import me.nasrabadiam.tictactoe.game.model.Player
import me.nasrabadiam.tictactoe.game.model.WiningOrientation
import me.nasrabadiam.tictactoe.game.model.evaluateBoard
import me.nasrabadiam.tictactoe.game.model.utlis.listOfEmptyCells
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class BoardEvaluationTest {

    // Test Row Wins
    @Test
    fun evaluateBoard_returns_winner_for_top_row_X() {
        val cells = listOfEmptyCells().toMutableList()
        cells[0] = cells[0].copy(value = Player.X)
        cells[1] = cells[1].copy(value = Player.X)
        cells[2] = cells[2].copy(value = Player.X)

        val result = evaluateBoard(cells)

        assertEquals(
            GameResult.EndWithWinner(
                player = Player.X,
                winningOrientation = WiningOrientation.ROW,
                winningIndex = 0
            ),
            result
        )
    }

    @Test
    fun evaluateBoard_returns_winner_for_middle_row_O() {
        val cells = listOfEmptyCells().toMutableList()
        cells[3] = cells[3].copy(value = Player.O)
        cells[4] = cells[4].copy(value = Player.O)
        cells[5] = cells[5].copy(value = Player.O)

        val result = evaluateBoard(cells)

        assertEquals(
            GameResult.EndWithWinner(
                player = Player.O,
                winningOrientation = WiningOrientation.ROW,
                winningIndex = 1
            ),
            result
        )
    }

    @Test
    fun evaluateBoard_returns_winner_for_bottom_row_X() {
        val cells = listOfEmptyCells().toMutableList()
        cells[6] = cells[6].copy(value = Player.X)
        cells[7] = cells[7].copy(value = Player.X)
        cells[8] = cells[8].copy(value = Player.X)

        val result = evaluateBoard(cells)

        assertEquals(
            GameResult.EndWithWinner(
                player = Player.X,
                winningOrientation = WiningOrientation.ROW,
                winningIndex = 2
            ),
            result
        )
    }

    // Test Column Wins
    @Test
    fun evaluateBoard_returns_winner_for_left_column_X() {
        val cells = listOfEmptyCells().toMutableList()
        cells[0] = cells[0].copy(value = Player.X)
        cells[3] = cells[3].copy(value = Player.X)
        cells[6] = cells[6].copy(value = Player.X)

        val result = evaluateBoard(cells)

        assertEquals(
            GameResult.EndWithWinner(
                player = Player.X,
                winningOrientation = WiningOrientation.COLUMN,
                winningIndex = 0
            ),
            result
        )
    }

    @Test
    fun evaluateBoard_returns_winner_for_middle_column_O() {
        val cells = listOfEmptyCells().toMutableList()
        cells[1] = cells[1].copy(value = Player.O)
        cells[4] = cells[4].copy(value = Player.O)
        cells[7] = cells[7].copy(value = Player.O)

        val result = evaluateBoard(cells)

        assertEquals(
            GameResult.EndWithWinner(
                player = Player.O,
                winningOrientation = WiningOrientation.COLUMN,
                winningIndex = 1
            ),
            result
        )
    }

    @Test
    fun evaluateBoard_returns_winner_for_right_column_X() {
        val cells = listOfEmptyCells().toMutableList()
        cells[2] = cells[2].copy(value = Player.X)
        cells[5] = cells[5].copy(value = Player.X)
        cells[8] = cells[8].copy(value = Player.X)

        val result = evaluateBoard(cells)

        assertEquals(
            GameResult.EndWithWinner(
                player = Player.X,
                winningOrientation = WiningOrientation.COLUMN,
                winningIndex = 2
            ),
            result
        )
    }

    // Test Diagonal Wins
    @Test
    fun evaluateBoard_returns_winner_for_main_diagonal_X() {
        val cells = listOfEmptyCells().toMutableList()
        cells[0] = cells[0].copy(value = Player.X)
        cells[4] = cells[4].copy(value = Player.X)
        cells[8] = cells[8].copy(value = Player.X)

        val result = evaluateBoard(cells)

        assertEquals(
            GameResult.EndWithWinner(
                player = Player.X,
                winningOrientation = WiningOrientation.CROSS,
                winningIndex = 0
            ),
            result
        )
    }

    @Test
    fun evaluateBoard_returns_winner_for_anti_diagonal_O() {
        val cells = listOfEmptyCells().toMutableList()
        cells[2] = cells[2].copy(value = Player.O)
        cells[4] = cells[4].copy(value = Player.O)
        cells[6] = cells[6].copy(value = Player.O)

        val result = evaluateBoard(cells)

        assertEquals(
            GameResult.EndWithWinner(
                player = Player.O,
                winningOrientation = WiningOrientation.CROSS,
                winningIndex = 1
            ),
            result
        )
    }

    // Test Draw
    @Test
    fun evaluateBoard_returns_draw_when_board_is_full_with_no_winner() {
        val cells = listOf(
            Cell(0, Player.X), Cell(1, Player.O), Cell(2, Player.X),
            Cell(3, Player.O), Cell(4, Player.X), Cell(5, Player.O),
            Cell(6, Player.O), Cell(7, Player.X), Cell(8, Player.O)
        )

        val result = evaluateBoard(cells)

        assertEquals(GameResult.Draw, result)
    }

    // Test Ongoing Game
    @Test
    fun evaluateBoard_returns_null_when_game_is_ongoing() {
        val cells = listOfEmptyCells().toMutableList()
        cells[0] = cells[0].copy(value = Player.X)
        cells[1] = cells[1].copy(value = Player.O)

        val result = evaluateBoard(cells)

        assertNull(result)
    }

    @Test
    fun evaluateBoard_returns_null_for_empty_board() {
        val cells = listOfEmptyCells()

        val result = evaluateBoard(cells)

        assertNull(result)
    }

    // Test Edge Cases
    @Test
    fun evaluateBoard_returns_winner_when_mixed_with_empty_cells() {
        val cells = listOfEmptyCells().toMutableList()
        cells[0] = cells[0].copy(value = Player.X)
        cells[4] = cells[4].copy(value = Player.X)
        cells[8] = cells[8].copy(value = Player.X)
        cells[1] = cells[1].copy(value = Player.O)

        val result = evaluateBoard(cells)

        assertEquals(
            GameResult.EndWithWinner(
                player = Player.X,
                winningOrientation = WiningOrientation.CROSS,
                winningIndex = 0
            ),
            result
        )
    }

    @Test
    fun evaluateBoard_returns_first_winning_pattern_found() {
        // Create a scenario where multiple winning patterns exist
        val cells = listOf(
            Cell(0, Player.X), Cell(1, Player.X), Cell(2, Player.X),
            Cell(3, Player.X), Cell(4, Player.X), Cell(5, Player.O),
            Cell(6, Player.X), Cell(7, Player.O), Cell(8, Player.O)
        )

        val result = evaluateBoard(cells)

        // Should return the first winning pattern found (top row)
        assertEquals(
            GameResult.EndWithWinner(
                player = Player.X,
                winningOrientation = WiningOrientation.ROW,
                winningIndex = 0
            ),
            result
        )
    }

    @Test
    fun evaluateBoard_handles_mixed_players_no_winner() {
        val cells = listOfEmptyCells().toMutableList()
        cells[0] = cells[0].copy(value = Player.X)
        cells[1] = cells[1].copy(value = Player.O)
        cells[2] = cells[2].copy(value = Player.X)
        cells[3] = cells[3].copy(value = Player.O)

        val result = evaluateBoard(cells)

        assertNull(result)
    }

    @Test
    fun evaluateBoard_handles_single_cell_filled() {
        val cells = listOfEmptyCells().toMutableList()
        cells[4] = cells[4].copy(value = Player.X)

        val result = evaluateBoard(cells)

        assertNull(result)
    }

    @Test
    fun evaluateBoard_handles_two_in_a_row_no_winner() {
        val cells = listOfEmptyCells().toMutableList()
        cells[0] = cells[0].copy(value = Player.X)
        cells[1] = cells[1].copy(value = Player.X)
        // Missing third cell for win

        val result = evaluateBoard(cells)

        assertNull(result)
    }
}