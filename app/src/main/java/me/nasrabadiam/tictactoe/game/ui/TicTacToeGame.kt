package me.nasrabadiam.tictactoe.game.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import me.nasrabadiam.tictactoe.game.model.Cell
import me.nasrabadiam.tictactoe.game.model.GameResult
import me.nasrabadiam.tictactoe.game.model.GameResult.Draw
import me.nasrabadiam.tictactoe.game.model.GameResult.EndWithWinner
import me.nasrabadiam.tictactoe.game.model.Player.O
import me.nasrabadiam.tictactoe.game.model.Player.X
import me.nasrabadiam.tictactoe.game.model.utlis.getBoardSize
import me.nasrabadiam.tictactoe.game.model.utlis.listOfEmptyCells
import me.nasrabadiam.tictactoe.ui.squareWrapContentLayout
import me.nasrabadiam.tictactoe.ui.theme.transparent

@Composable
fun TicTacToeGameBoard(
    cellsData: List<Cell>,
    gameResult: GameResult?,
    onReplayClicked: () -> Unit,
    onCellClicked: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        val transparent = MaterialTheme.colorScheme.transparent
        val onBackground = MaterialTheme.colorScheme.onBackground

        val resultBackgroundColor = if (gameResult == null) {
            transparent
        } else {
            onBackground
        }
        val blur = if (gameResult == null) 0.dp else 4.dp

        GameGrid(cellsData, onCellClicked, modifier.blur(blur))
        if (gameResult != null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.radialGradient(
                            colors = listOf(
                                resultBackgroundColor,
                                resultBackgroundColor.copy(alpha = 0.7f),
                                transparent
                            ),
                        )
                    )
            )

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                val resultText = when (gameResult) {
                    Draw -> "Draw"
                    is EndWithWinner -> "${gameResult.player.name} Wins"
                }

                Text(
                    text = resultText,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.displayLarge,
                    color = MaterialTheme.colorScheme.background,
                    modifier = Modifier.padding(8.dp)
                )
                Button(
                    onClick = onReplayClicked,
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    Text("Again")
                }
            }
        }
    }
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
            .squareWrapContentLayout()
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
        gameResult = null,
        onReplayClicked = {},
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