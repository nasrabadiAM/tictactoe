package me.nasrabadiam.tictactoe.game

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.nasrabadiam.tictactoe.game.utlis.getBoardSize
import me.nasrabadiam.tictactoe.game.utlis.listOfEmptyCells

@Composable
fun TicTacToeGameBoard(
    cellsData: List<Cell>,
    onCellClicked: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    GameGrid(cellsData, onCellClicked, modifier)
}

@Composable
private fun GameGrid(
    cellsData: List<Cell>,
    onCellClicked: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val boardSize = cellsData.getBoardSize()

    Column(modifier.testTag("game_board")) {
        (0 until boardSize).forEach { index ->
            GameRow(
                cellsData = cellsData,
                rowIndex = index,
                boardSize = boardSize,
                onCellClicked = onCellClicked,
                modifier = Modifier.wrapContentHeight()
            )
        }
    }
}

@Composable
private fun GameRow(
    cellsData: List<Cell>,
    rowIndex: Int,
    boardSize: Int,
    onCellClicked: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier) {
        (0 until boardSize).forEach { columnIndex ->
            val itemIndex = (rowIndex * boardSize) + columnIndex
            val cell = cellsData[itemIndex]
            GameCell(cell, onCellClicked)
        }
    }
}

@Composable
private fun GameCell(
    cell: Cell,
    onClick: (Int) -> Unit
) {
    Text(
        modifier = Modifier
            .size(48.dp)
            .padding(8.dp)
            .clickable { onClick.invoke(cell.index) }
            .semantics { testTag = "cell_${cell.index}" },
        color = MaterialTheme.colorScheme.onBackground,
        text = cell.getShowingValue()
    )
}

@Composable
private fun VerticalDivider() {
    Box(
        modifier = Modifier
            .height(36.dp)
            .width(2.dp)
            .semantics { testTag = "grid" }
            .background(MaterialTheme.colorScheme.onBackground)
    )
}

@Preview(showBackground = true)
@Composable
fun TicTacToePreview() {
    TicTacToeGameBoard(
        cellsData = listOfEmptyCells(DEFAULT_BOARD_CELL_COUNT),
        onCellClicked = {},
        modifier = Modifier
            .wrapContentSize()
            .padding(8.dp)
    )
}