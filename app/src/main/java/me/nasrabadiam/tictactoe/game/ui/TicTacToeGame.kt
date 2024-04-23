package me.nasrabadiam.tictactoe.game.ui

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
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

        val blurValue by animateDpAsState(
            targetValue = if (gameResult == null) 0.dp else 4.dp,
            label = "Blur",
            animationSpec = tween(durationMillis = GAME_RESULT_ANIMATION_DURATION)
        )
        GameGrid(cellsData, onCellClicked, modifier.blur(blurValue))
        if (gameResult != null) {
            GameResult(gameResult, onReplayClicked)
        }
    }
}

@Composable
private fun GameResult(
    gameResult: GameResult,
    onReplayClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val transparent = MaterialTheme.colorScheme.transparent
    val targetColor = MaterialTheme.colorScheme.surface
    val alpha = remember {
        Animatable(
            initialValue = 0f,
        )
    }

    LaunchedEffect(gameResult) {
        alpha.animateTo(
            targetValue = 1f,
            animationSpec = tween(GAME_RESULT_ANIMATION_DURATION)
        )
    }
    Box(
        modifier = modifier
            .squareWrapContentLayout()
            .blur(16.dp)
            .background(
                Brush.radialGradient(
                    colors = listOf(
                        targetColor.copy(alpha.value),
                        targetColor.copy((alpha.value - 0.2f).coerceIn(0f, 1f)),
                        transparent
                    ),
                )
            )
    )

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val resultText = when (gameResult) {
            Draw -> "Draw!"
            is EndWithWinner -> "${gameResult.player.name} Wins"
        }

        Text(
            text = resultText,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.displayLarge,
            color = MaterialTheme.colorScheme.onSurface,
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
    @PreviewParameter(GameBoardDataProvider::class) gameBoardData: Pair<GameResult?, List<Cell>>
) {
    val (gameResult, cellsData) = gameBoardData
    TicTacToeGameBoard(
        cellsData = cellsData,
        onCellClicked = {},
        gameResult = gameResult,
        onReplayClicked = {},
        modifier = Modifier
            .wrapContentSize()
            .padding(8.dp)
    )
}

private const val GAME_RESULT_ANIMATION_DURATION = 300

class GameBoardDataProvider : PreviewParameterProvider<Pair<GameResult?, List<Cell>>> {

    override val values: Sequence<Pair<GameResult?, List<Cell>>>
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
            return sequenceOf(
                Pair(null, mixedCellsData),
                Pair(Draw, oCellsData),
                Pair(null, xCellsData)
            )
        }
}