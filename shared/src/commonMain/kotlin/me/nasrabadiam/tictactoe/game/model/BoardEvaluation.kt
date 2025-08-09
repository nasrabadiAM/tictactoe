package me.nasrabadiam.tictactoe.game.model

internal fun evaluateBoard(cells: List<Cell>): GameResult? {
    // Check all winning patterns
    WINNING_PATTERNS.forEach { pattern ->
        val values = pattern.cells.map { cells[it].value }
        val player = values.firstOrNull()
        if (player != null && values.all { it == player }) {
            return GameResult.EndWithWinner(
                player = player,
                winningOrientation = pattern.orientation,
                winningIndex = pattern.index
            )
        }
    }

    // Check for draw
    return if (cells.all { it.value != null }) {
        GameResult.Draw
    } else {
        null // Game still ongoing
    }
}

private data class WinPattern(
    val cells: List<Int>,
    val orientation: WiningOrientation,
    val index: Int
)

private val WINNING_PATTERNS = buildList {
    // Rows (0-2)
    repeat(BOARD_SIZE) { row ->
        val cells = (0 until BOARD_SIZE).map { col ->
            row * BOARD_SIZE + col
        }
        add(WinPattern(cells, WiningOrientation.ROW, row))
    }

    // Columns (0-2)
    repeat(BOARD_SIZE) { col ->
        val cells = (0 until BOARD_SIZE).map { row ->
            row * BOARD_SIZE + col
        }
        add(WinPattern(cells, WiningOrientation.COLUMN, col))
    }

    // Main diagonal (top-left to bottom-right)
    val mainDiagonal = (0 until BOARD_SIZE).map { i ->
        i * BOARD_SIZE + i
    }
    add(WinPattern(mainDiagonal, WiningOrientation.CROSS, 0))

    // Anti-diagonal (top-right to bottom-left)
    val antiDiagonal = (0 until BOARD_SIZE).map { i ->
        i * BOARD_SIZE + (BOARD_SIZE - 1 - i)
    }
    add(WinPattern(antiDiagonal, WiningOrientation.CROSS, 1))
}