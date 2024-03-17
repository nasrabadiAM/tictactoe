package me.nasrabadiam.tictactoe

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import me.nasrabadiam.tictactoe.game.model.Cell
import me.nasrabadiam.tictactoe.game.model.DEFAULT_BOARD_CELL_COUNT
import me.nasrabadiam.tictactoe.game.model.GameResult
import me.nasrabadiam.tictactoe.game.model.GameResult.Draw
import me.nasrabadiam.tictactoe.game.model.GameResult.EndWithWinner
import me.nasrabadiam.tictactoe.game.GameUseCase
import me.nasrabadiam.tictactoe.game.model.Player.X
import me.nasrabadiam.tictactoe.game.ui.TicTacToeGameBoard
import me.nasrabadiam.tictactoe.game.model.utlis.listOfEmptyCells
import me.nasrabadiam.tictactoe.ui.theme.TicTacToeTheme

@Composable
fun MainScreen(gameUseCase: GameUseCase, isExpandedScreen: Boolean) {
    TicTacToeTheme {
        val cells = gameUseCase.cells.collectAsState(
            initial = listOfEmptyCells(DEFAULT_BOARD_CELL_COUNT)
        )
        val gameResult = gameUseCase.gameResult.collectAsState()
        MainScreenContent(
            cellsData = cells.value,
            gameResult = gameResult.value,
            onCellClicked = gameUseCase::clickOnCell,
            onRestartClicked = gameUseCase::restartGame,
            isExpandedScreen = isExpandedScreen,
        )
    }
}

@Composable
private fun MainScreenContent(
    cellsData: List<Cell>,
    gameResult: GameResult?,
    onCellClicked: (Int) -> Unit,
    onRestartClicked: () -> Unit,
    isExpandedScreen: Boolean,
    modifier: Modifier = Modifier
) {

    if (isExpandedScreen) {
        HorizontalScreen(
            modifier,
            gameResult,
            onRestartClicked,
            cellsData,
            onCellClicked
        )
    } else {
        VerticalScreen(
            modifier,
            gameResult,
            onRestartClicked,
            cellsData,
            onCellClicked
        )
    }
}

@Composable
private fun VerticalScreen(
    modifier: Modifier,
    gameResult: GameResult?,
    onRestartClicked: () -> Unit,
    cellsData: List<Cell>,
    onCellClicked: (Int) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
    ) {
        val result = getResultString(gameResult)
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = onRestartClicked) {
                Text(text = "Restart")
            }
            Text(
                text = result,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
        TicTacToeGameBoard(
            cellsData = cellsData,
            onCellClicked = onCellClicked,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Composable
private fun HorizontalScreen(
    modifier: Modifier,
    gameResult: GameResult?,
    onRestartClicked: () -> Unit,
    cellsData: List<Cell>,
    onCellClicked: (Int) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
    ) {
        val result = getResultString(gameResult)
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = onRestartClicked) {
                Text(text = "Restart")
            }
            Text(
                text = result,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
        TicTacToeGameBoard(
            cellsData = cellsData,
            onCellClicked = onCellClicked,
            modifier = Modifier.padding(8.dp)
        )
    }
}

private fun getResultString(gameResult: GameResult?) = when (gameResult) {
    is Draw -> "Draw"
    is EndWithWinner -> "${gameResult.player} Wins"
    else -> ""
}

@Preview(showBackground = true)
@Preview(showSystemUi = true, device = Devices.TABLET)
@Composable
fun MainScreenPreview(
    @PreviewParameter(IsExpandedScreenDataProvider::class) isExpandedScreen: Boolean

) {
    TicTacToeTheme {
        val cells = listOfEmptyCells(DEFAULT_BOARD_CELL_COUNT)
        MainScreenContent(
            cellsData = cells,
            gameResult = EndWithWinner(X),
            onCellClicked = {},
            onRestartClicked = {},
            isExpandedScreen = isExpandedScreen
        )
    }
}

class IsExpandedScreenDataProvider : PreviewParameterProvider<Boolean> {

    override val values: Sequence<Boolean>
        get() {
            return sequenceOf(true, false)
        }
}