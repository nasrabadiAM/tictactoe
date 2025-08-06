package me.nasrabadiam.wearapp.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.TimeText
import androidx.wear.compose.material.curvedText
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
            TimeText(
                startLinearContent = {
                    Text(
                        text = "(O: ${state.scores.oScore})",
                        fontWeight = state.currentPlayer.getFontWeight(state.currentPlayer),
                    )
                },
                startCurvedContent = {
                    curvedText(
                        text = "(O: ${state.scores.oScore})",
                        fontWeight = state.currentPlayer.getFontWeight(state.currentPlayer),
                    )
                },
                endCurvedContent = {
                    curvedText(
                        text = "(X: ${state.scores.xScore})",
                        fontWeight = state.currentPlayer.getFontWeight(state.currentPlayer),
                    )
                },
                endLinearContent = {
                    Text(
                        text = "(X: ${state.scores.xScore})",
                        fontWeight = state.currentPlayer.getFontWeight(state.currentPlayer),
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

private fun Player.getFontWeight(currentPlayer: Player): FontWeight {
    return if (currentPlayer == this) FontWeight.Bold else FontWeight.Normal
}

