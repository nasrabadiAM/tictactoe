package me.nasrabadiam.tictactoe.game.ui

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import me.nasrabadiam.tictactoe.game.model.Cell
import me.nasrabadiam.tictactoe.game.model.GameResult
import me.nasrabadiam.tictactoe.game.model.GameResult.Draw
import me.nasrabadiam.tictactoe.game.model.Player.O
import me.nasrabadiam.tictactoe.game.model.Player.X
import me.nasrabadiam.tictactoe.game.model.utlis.getBoardSize
import me.nasrabadiam.tictactoe.game.model.utlis.listOfEmptyCells
import me.nasrabadiam.tictactoe.ui.squareWrapContentLayout
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.ui.tooling.preview.PreviewParameter
import org.jetbrains.compose.ui.tooling.preview.PreviewParameterProvider

@Composable
fun TicTacToeGameBoard(
    cellsData: List<Cell>,
    gameResult: GameResult?,
    onReplayClicked: () -> Unit,
    onCellClicked: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        val winnerAnimation = remember { Animatable(0f) }
        LaunchedEffect(gameResult) {
            if (gameResult == null) {
                winnerAnimation.snapTo(0f)
            }
        }

        val blurValue by animateDpAsState(
            targetValue = if (gameResult == null || winnerAnimation.isRunning) 0.dp else 4.dp,
            label = "Blur",
            animationSpec = tween(
                durationMillis = GAME_RESULT_ANIMATION_DURATION,
                delayMillis = GAME_RESULT_ANIMATION_DELAY.toInt()
            )
        )
        GameGrid(
            cellsData, onCellClicked,
            modifier.blur(blurValue)
        )
        if (gameResult != null) {
            GameResultBox(
                gameResult = gameResult,
                backgroundBlur = blurValue,
                winnerAnimation = winnerAnimation,
                onReplayClicked = onReplayClicked,
            )
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

@Preview
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