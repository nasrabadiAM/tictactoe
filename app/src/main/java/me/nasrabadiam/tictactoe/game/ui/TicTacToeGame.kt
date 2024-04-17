package me.nasrabadiam.tictactoe.game.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import me.nasrabadiam.tictactoe.game.model.Cell
import me.nasrabadiam.tictactoe.game.model.Player.O
import me.nasrabadiam.tictactoe.game.model.Player.X
import me.nasrabadiam.tictactoe.game.model.utlis.getBoardSize
import me.nasrabadiam.tictactoe.game.model.utlis.listOfEmptyCells
import me.nasrabadiam.tictactoe.ui.squareLayout

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

    Column(
        modifier = modifier
            .squareLayout()
            .testTag("game_board")
    ) {
        for (index in 0 until boardSize) {
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
private fun GameRow(
    cellsData: List<Cell>,
    rowIndex: Int,
    boardSize: Int,
    onCellClicked: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier) {
        for (columnIndex in 0 until boardSize) {
            val itemIndex = (rowIndex * boardSize) + columnIndex
            val cell = cellsData[itemIndex]
            GameCell(cell, onCellClicked)
        }
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
            val mixedCellsData = listOfEmptyCells().map {
                if (it.index % 2 == 0) {
                    it.copy(value = O)
                } else {
                    it.copy(value = X)
                }
            }
            val xCellsData = listOfEmptyCells().map {
                it.copy(value = X)
            }
            val oCellsData = listOfEmptyCells().map {
                it.copy(value = X)
            }
            return sequenceOf(mixedCellsData, oCellsData, xCellsData)
        }
}