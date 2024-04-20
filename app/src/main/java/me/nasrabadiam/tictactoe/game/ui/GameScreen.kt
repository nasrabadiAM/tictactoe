package me.nasrabadiam.tictactoe.game.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import me.nasrabadiam.tictactoe.GameWindowSizeClass
import me.nasrabadiam.tictactoe.GameWindowSizeClass.COMPACT
import me.nasrabadiam.tictactoe.GameWindowSizeClass.EXPANDED
import me.nasrabadiam.tictactoe.GameWindowSizeClass.NORMAL
import me.nasrabadiam.tictactoe.game.ui.GameEvent.CellClicked
import me.nasrabadiam.tictactoe.game.ui.GameEvent.RestartClicked
import me.nasrabadiam.tictactoe.game.ui.GameEvent.RulesClicked
import me.nasrabadiam.tictactoe.ui.theme.TicTacToeTheme

@Composable
fun GameScreen(
    gameViewModel: GameViewModel,
    windowSizeClass: GameWindowSizeClass,
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
    val modifierWithBackground = modifier.background(MaterialTheme.colorScheme.surfaceDim)
    when (windowSizeClass) {
        NORMAL -> VerticalGameScreen(
            modifierWithBackground,
            state,
            sendEvent,
        )

        EXPANDED -> HorizontalGameScreen(
            modifierWithBackground,
            state,
            sendEvent
        )

        COMPACT -> CompactGameScreen(
            modifierWithBackground,
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
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize(),
    ) {
        ScoresSection(
            scores = state.scores
        )

        TicTacToeGameBoard(
            cellsData = state.cells, onCellClicked = { index ->
                sendEvent(CellClicked(index))
            },
            gameResult = state.gameResult,
            onReplayClicked = { sendEvent(GameEvent.ReplayClicked) },
            modifier = Modifier
                .weight(1f)
                .padding(8.dp)
        )
        ButtonsSection(
            sendEvent = sendEvent
        )
    }
}

@Composable
private fun HorizontalGameScreen(
    modifier: Modifier,
    state: GameState,
    sendEvent: (GameEvent) -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxSize(),
    ) {
        ScoresSection(
            scores = state.scores,
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
        )

        TicTacToeGameBoard(
            cellsData = state.cells, onCellClicked = { index ->
                sendEvent(CellClicked(index))
            },
            gameResult = state.gameResult,
            onReplayClicked = { sendEvent(GameEvent.ReplayClicked) },
            modifier = Modifier
                .fillMaxHeight()
                .padding(8.dp)
        )
        ButtonsSection(
            sendEvent = sendEvent,
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
        )
    }
}

@Composable
private fun CompactGameScreen(
    modifier: Modifier,
    state: GameState,
    sendEvent: (GameEvent) -> Unit,
) {
    Column(modifier) {
        BoxWithConstraints(Modifier.weight(1f)) {
            if (maxWidth > maxHeight) {
                Row {
                    ScoresSection(
                        scores = state.scores,
                        modifier = Modifier
                            .weight(0.1f)
                    )
                    TicTacToeGameBoard(
                        cellsData = state.cells, onCellClicked = { index ->
                            sendEvent(CellClicked(index))
                        },
                        gameResult = state.gameResult,
                        onReplayClicked = { sendEvent(GameEvent.ReplayClicked) },
                        modifier = Modifier
                            .weight(1f)
                            .padding(8.dp)
                    )
                }
            } else {
                Column {
                    ScoresSection(
                        scores = state.scores,
                        modifier = Modifier
                            .weight(0.1f)
                    )
                    TicTacToeGameBoard(
                        cellsData = state.cells, onCellClicked = { index ->
                            sendEvent(CellClicked(index))
                        },
                        gameResult = state.gameResult,
                        onReplayClicked = { sendEvent(GameEvent.ReplayClicked) },
                        modifier = Modifier
                            .weight(1f)
                            .padding(8.dp)
                    )
                }
            }
        }
        Row(Modifier.weight(0.2f)) {

            Button(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .weight(1f)
                    .widthIn(min = 124.dp, max = 210.dp),
                onClick = { sendEvent(RestartClicked) }
            ) {
                Icon(
                    imageVector = Icons.Rounded.Refresh,
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = RESTART_BUTTON_TEXT)
            }
            Button(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .weight(1f)
                    .widthIn(min = 124.dp, max = 210.dp),
                enabled = false,
                onClick = { sendEvent(RulesClicked) }
            ) {
                Text(text = GAME_RULE_BUTTON_TEXT)
                Text(
                    text = COMING_SOON_BUTTON_TEXT,
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }
    }
}

@Composable
private fun ButtonsSection(
    sendEvent: (GameEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(
            vertical = 32.dp,
            horizontal = 8.dp
        ),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ActionButtons(onRestartClicked = {
            sendEvent(RestartClicked)
        }, onRulesClicked = {
            sendEvent(RulesClicked)
        })
    }
}

@Composable
private fun ActionButtons(
    onRestartClicked: () -> Unit,
    onRulesClicked: () -> Unit,
) {

    Button(
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .fillMaxWidth()
            .widthIn(min = 124.dp, max = 210.dp),
        onClick = onRestartClicked
    ) {
        Icon(imageVector = Icons.Rounded.Refresh, contentDescription = null)
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = RESTART_BUTTON_TEXT,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
    }
    Button(
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .fillMaxWidth()
            .widthIn(min = 124.dp, max = 210.dp),
        enabled = false,
        onClick = onRulesClicked
    ) {
        Text(text = GAME_RULE_BUTTON_TEXT)
        Text(
            text = COMING_SOON_BUTTON_TEXT,
            style = MaterialTheme.typography.labelSmall
        )
    }
}

private const val RESTART_BUTTON_TEXT = "Restart"
private const val GAME_RULE_BUTTON_TEXT = "Game Rules"
private const val COMING_SOON_BUTTON_TEXT = "(Coming Soon)"

@Preview(showBackground = true)
@Preview(showSystemUi = true, device = Devices.TABLET)
@Composable
fun MainScreenPreview(
    @PreviewParameter(WindowScreenSizeDataProvider::class) windowSizeClass: GameWindowSizeClass

) {
    MainScreenContent(
        state = GameState(), sendEvent = {}, windowSizeClass = windowSizeClass
    )
}

class WindowScreenSizeDataProvider : PreviewParameterProvider<GameWindowSizeClass> {

    override val values: Sequence<GameWindowSizeClass>
        get() {
            return sequenceOf(NORMAL, COMPACT, EXPANDED)
        }
}