package me.nasrabadiam.tictactoe

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import me.nasrabadiam.tictactoe.GameWindowSizeClass.COMPACT
import me.nasrabadiam.tictactoe.GameWindowSizeClass.EXPANDED
import me.nasrabadiam.tictactoe.GameWindowSizeClass.NORMAL
import me.nasrabadiam.tictactoe.game.model.GameResult
import me.nasrabadiam.tictactoe.game.model.GameResult.Draw
import me.nasrabadiam.tictactoe.game.model.GameResult.EndWithWinner
import me.nasrabadiam.tictactoe.game.ui.TicTacToeGameBoard
import me.nasrabadiam.tictactoe.ui.theme.TicTacToeTheme

@Composable
fun MainScreen(
    gameViewModel: GameViewModel,
    windowSizeClass: GameWindowSizeClass
) {
    TicTacToeTheme {
        val state = gameViewModel.state.collectAsState()
        MainScreenContent(
            state = state.value,
            sendEvent = gameViewModel::handleEvent,
            windowSizeClass = windowSizeClass,
        )
    }
}

@Composable
private fun MainScreenContent(
    state: GameState,
    sendEvent: (GameEvent) -> Unit,
    windowSizeClass: GameWindowSizeClass,
    modifier: Modifier = Modifier
) {
    when (windowSizeClass) {
        NORMAL -> VerticalGameScreen(
            modifier,
            state,
            sendEvent,
        )

        EXPANDED -> HorizontalGameScreen(
            modifier,
            state,
            sendEvent
        )

        COMPACT -> CompactGameScreen(
            modifier,
            state,
            sendEvent
        )
    }
}

@Composable
private fun VerticalGameScreen(
    modifier: Modifier,
    state: GameState,
    sendEvent: (GameEvent) -> Unit,
) {
    val isExpandedScreen = false
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
    ) {
        ScoresSection(
            state.scores,
            isExpandedScreen
        )

        TicTacToeGameBoard(
            cellsData = state.cells,
            onCellClicked = { index ->
                sendEvent(GameEvent.CellClicked(index))
            },
            modifier = Modifier
                .weight(1f)
                .padding(8.dp)
        )
        ButtonsSection(
            state.gameResult,
            sendEvent,
            isExpandedScreen
        )
    }
}

@Composable
private fun HorizontalGameScreen(
    modifier: Modifier,
    state: GameState,
    sendEvent: (GameEvent) -> Unit,
) {
    val isExpandedScreen = true
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
    ) {
        ScoresSection(
            state.scores,
            isExpandedScreen
        )
        TicTacToeGameBoard(
            cellsData = state.cells,
            onCellClicked = { index ->
                sendEvent(GameEvent.CellClicked(index))
            },
            modifier = Modifier
                .weight(1f)
                .padding(8.dp)
        )
        ButtonsSection(
            state.gameResult,
            sendEvent,
            isExpandedScreen
        )
    }
}

@Composable
private fun CompactGameScreen(
    modifier: Modifier,
    state: GameState,
    sendEvent: (GameEvent) -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
    ) {
        Column {
            Scores(state.scores)
            val result = getResultString(state.gameResult)

            ActionButtons(
                result,
                state.gameResult,
                onRestartClicked = {
                    sendEvent(GameEvent.RestartClicked)
                },
                onReplayClicked = {
                    sendEvent(GameEvent.ReplayClicked)
                }
            )
        }
        TicTacToeGameBoard(
            cellsData = state.cells,
            onCellClicked = { index ->
                sendEvent(GameEvent.CellClicked(index))

            },
            modifier = Modifier
                .weight(1f)
                .padding(8.dp)
        )
    }
}

@Composable
private fun ButtonsSection(
    gameResult: GameResult?,
    sendEvent: (GameEvent) -> Unit,
    isExpandedScreen: Boolean,
    modifier: Modifier = Modifier
) {
    val result = getResultString(gameResult)
    if (isExpandedScreen) {
        Column(
            modifier = modifier.padding(
                vertical = 8.dp,
                horizontal = 32.dp
            ),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ActionButtons(
                result,
                gameResult,
                onRestartClicked = {
                    sendEvent(GameEvent.RestartClicked)
                },
                onReplayClicked = {
                    sendEvent(GameEvent.ReplayClicked)
                }
            )
        }
    } else {
        Row(
            modifier = modifier.padding(
                vertical = 32.dp,
                horizontal = 8.dp
            ),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ActionButtons(
                result,
                gameResult,
                onRestartClicked = {
                    sendEvent(GameEvent.RestartClicked)
                },
                onReplayClicked = {
                    sendEvent(GameEvent.ReplayClicked)
                }
            )
        }
    }
}

@Composable
private fun ActionButtons(
    result: String,
    gameResult: GameResult?,
    onRestartClicked: () -> Unit,
    onReplayClicked: () -> Unit
) {
    Text(
        text = result,
        color = MaterialTheme.colorScheme.onBackground
    )
    Button(onClick = onRestartClicked) {
        Text(text = "Restart")
    }
    if (gameResult != null) {
        Button(onClick = onReplayClicked) {
            Text(text = "Again")
        }
    }
}

@Composable
private fun ScoresSection(
    scores: ScoresState,
    isExpandedScreen: Boolean,
    modifier: Modifier = Modifier
) {
    if (isExpandedScreen) {
        Column(
            verticalArrangement = Arrangement.SpaceAround,
            modifier = modifier
                .fillMaxHeight()
                .padding(
                    vertical = 8.dp,
                    horizontal = 32.dp
                )
        ) {
            Scores(scores)
        }
    } else {
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = modifier
                .fillMaxWidth()
                .padding(
                    vertical = 32.dp,
                    horizontal = 8.dp
                )
        ) {
            Scores(scores)
        }
    }
}

@Composable
private fun Scores(scores: ScoresState) {

    Text(
        modifier = Modifier.padding(4.dp),
        text = "X: ${scores.xScore}",
        color = MaterialTheme.colorScheme.onBackground
    )
    Text(
        modifier = Modifier.padding(4.dp),
        text = "Draw: ${scores.drawCount}",
        color = MaterialTheme.colorScheme.onBackground
    )
    Text(
        modifier = Modifier.padding(4.dp),
        text = "O: ${scores.oScore}",
        color = MaterialTheme.colorScheme.onBackground
    )
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
    @PreviewParameter(WindowScreenSizeDataProvider::class) windowSizeClass: GameWindowSizeClass

) {
    MainScreenContent(
        state = GameState(),
        sendEvent = {},
        windowSizeClass = windowSizeClass
    )
}

class WindowScreenSizeDataProvider : PreviewParameterProvider<GameWindowSizeClass> {

    override val values: Sequence<GameWindowSizeClass>
        get() {
            return sequenceOf(NORMAL, COMPACT, EXPANDED)
        }
}