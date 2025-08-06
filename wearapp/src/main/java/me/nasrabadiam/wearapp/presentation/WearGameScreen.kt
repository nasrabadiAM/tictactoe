package me.nasrabadiam.wearapp.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.TimeText
import androidx.wear.compose.material.curvedText
import androidx.wear.tooling.preview.devices.WearDevices
import me.nasrabadiam.tictactoe.game.model.Player
import me.nasrabadiam.tictactoe.game.ui.GameEvent
import me.nasrabadiam.tictactoe.game.ui.GameEvent.CellClicked
import me.nasrabadiam.tictactoe.game.ui.GameState
import me.nasrabadiam.tictactoe.game.ui.GameViewModel
import me.nasrabadiam.tictactoe.game.ui.TicTacToeGameBoard
import me.nasrabadiam.tictactoe.ui.theme.TacTrixTheme

@Composable
fun WearGameScreen(
    gameViewModel: GameViewModel,
) {
    TacTrixTheme {
        val state = gameViewModel.state.collectAsState()
        GameScreenContent(
            state = state.value,
            sendEvent = gameViewModel::handleEvent,
        )
    }
}

@Composable
private fun GameScreenContent(
    state: GameState,
    sendEvent: (GameEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        timeText = {
            val xScoreText = "(X: ${state.scores.xScore})"
            val oScoreText = "(O: ${state.scores.oScore})"
            TimeText(
                startLinearContent = {
                    Text(
                        text = oScoreText,
                        fontWeight = state.currentPlayer.getFontWeightOfPlayer(Player.O),
                    )
                },
                startCurvedContent = {
                    curvedText(
                        text = oScoreText,
                        fontWeight = state.currentPlayer.getFontWeightOfPlayer(Player.O),
                    )
                },
                endCurvedContent = {
                    curvedText(
                        text = xScoreText,
                        fontWeight = state.currentPlayer.getFontWeightOfPlayer(Player.X),
                    )
                },
                endLinearContent = {
                    Text(
                        text = xScoreText,
                        fontWeight = state.currentPlayer.getFontWeightOfPlayer(Player.X),
                    )
                }
            )
        }) {
        TicTacToeGameBoard(
            cellsData = state.cells, onCellClicked = { index ->
                sendEvent(CellClicked(index))
            },
            gameResult = state.gameResult,
            onReplayClicked = { sendEvent(GameEvent.ReplayClicked) },
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 12.dp, start = 8.dp, end = 8.dp, bottom = 8.dp)
        )
    }
}

private fun Player.getFontWeightOfPlayer(player: Player): FontWeight {
    return if (player == this) FontWeight.Bold else FontWeight.Normal
}

@Preview(device = WearDevices.SMALL_ROUND, showSystemUi = true)
@Preview(device = WearDevices.LARGE_ROUND, showSystemUi = true)
@Preview(device = WearDevices.SQUARE, showSystemUi = true)
@Preview(device = WearDevices.RECT, showSystemUi = true)
@Composable
fun WearGameScreenPreView() {
    TacTrixTheme {
        GameScreenContent(state = GameState(), sendEvent = {})
    }
}