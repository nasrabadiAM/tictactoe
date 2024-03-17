package me.nasrabadiam.tictactoe.game.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import me.nasrabadiam.tictactoe.game.model.Cell
import me.nasrabadiam.tictactoe.game.model.DEFAULT_BOARD_CELL_COUNT
import me.nasrabadiam.tictactoe.game.model.Player.O
import me.nasrabadiam.tictactoe.game.model.Player.X
import me.nasrabadiam.tictactoe.game.model.utlis.getBoardSize
import me.nasrabadiam.tictactoe.game.model.utlis.listOfEmptyCells
import me.nasrabadiam.tictactoe.ui.AutoSizeText

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

    val gridColor = MaterialTheme.colorScheme.onBackground
    Column(
        modifier = modifier
            .aspectRatio(1f)
            .fillMaxWidth()
            .drawBehind(gameGrid(boardSize, gridColor))
            .testTag("game_board")
    ) {
        (0 until boardSize).forEach { index ->
            GameRow(
                cellsData = cellsData,
                rowIndex = index,
                boardSize = boardSize,
                onCellClicked = onCellClicked,
                modifier = Modifier
                    .aspectRatio(3f)
                    .fillMaxWidth()
            )
        }
    }
}

@Composable
private fun gameGrid(
    boardSize: Int,
    gridColor: Color
): DrawScope.() -> Unit = {
    val canvasWidth = size.width
    val canvasHeight = size.height
    val horizontalLineY = canvasHeight / 3
    val verticalLineX = canvasWidth / 3
    val lineStrokeWidth = 1.dp.toPx()

    for (lineNumber in 1 until boardSize) {
        drawLine(
            start = Offset(x = 0.dp.toPx(), y = lineNumber * horizontalLineY),
            end = Offset(x = canvasWidth, y = lineNumber * horizontalLineY),
            color = gridColor,
            strokeWidth = lineStrokeWidth
        )
        drawLine(
            start = Offset(x = lineNumber * verticalLineX, y = 0.dp.toPx()),
            end = Offset(x = lineNumber * verticalLineX, y = canvasHeight),
            color = gridColor,
            strokeWidth = lineStrokeWidth
        )
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
    onClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier
            .padding(4.dp)
            .aspectRatio(1f)
            .clickable { onClick.invoke(cell.index) }
            .semantics { testTag = "cell_${cell.index}" },
        contentAlignment = Alignment.Center
    ) {

        val displayLargeStyle = MaterialTheme.typography.headlineSmall
        AutoSizeText(
            text = cell.getShowingValue(),
            textAlign = TextAlign.Center,
            style = displayLargeStyle,
            color = MaterialTheme.colorScheme.onBackground,
        )
    }
}

@Preview(showSystemUi = true)
@Preview(showSystemUi = true, device = Devices.TABLET)
@Composable
fun TicTacToePreview(
    @PreviewParameter(GameBoardDataProvider::class) cellsData: List<Cell>
) {
    TicTacToeGameBoard(
        cellsData = cellsData,
        onCellClicked = {},
        modifier = Modifier
            .wrapContentSize()
            .padding(8.dp)
    )
}

class GameBoardDataProvider : PreviewParameterProvider<List<Cell>> {

    override val values: Sequence<List<Cell>>
        get() {
            val mixedCellsData = listOfEmptyCells(DEFAULT_BOARD_CELL_COUNT).map {
                if (it.index % 2 == 0) {
                    it.copy(value = O)
                } else {
                    it.copy(value = X)
                }
            }
            val xCellsData = listOfEmptyCells(DEFAULT_BOARD_CELL_COUNT).map {
                it.copy(value = X)
            }
            val oCellsData = listOfEmptyCells(DEFAULT_BOARD_CELL_COUNT).map {
                it.copy(value = X)
            }
            return sequenceOf(mixedCellsData, oCellsData, xCellsData)
        }
}