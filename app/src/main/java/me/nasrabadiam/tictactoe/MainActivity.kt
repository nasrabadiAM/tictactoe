package me.nasrabadiam.tictactoe

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import me.nasrabadiam.tictactoe.game.Cell
import me.nasrabadiam.tictactoe.game.DEFAULT_BOARD_CELL_COUNT
import me.nasrabadiam.tictactoe.game.GameUseCase
import me.nasrabadiam.tictactoe.game.Players
import me.nasrabadiam.tictactoe.game.TicTacToeGameBoard
import me.nasrabadiam.tictactoe.game.utlis.listOfEmptyCells
import me.nasrabadiam.tictactoe.ui.theme.TicTacToeTheme

class MainActivity : ComponentActivity() {

    private val gameUseCase = GameUseCase()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen(gameUseCase)
        }
    }
}

@Composable
fun MainScreen(gameUseCase: GameUseCase) {
    TicTacToeTheme {
        val cells = gameUseCase.cells.collectAsState(
            initial = listOfEmptyCells(DEFAULT_BOARD_CELL_COUNT)
        )
        val gameResult = gameUseCase.gameResult.collectAsState()
        Log.d("TTT", "MainScreen: gameResult=$gameResult")
        MainScreenContent(
            cellsData = cells.value,
            gameResult = gameResult.value,
            onCellClicked = gameUseCase::onCellClicked
        )
    }
}

@Composable
private fun MainScreenContent(
    cellsData: List<Cell>,
    gameResult: Players?,
    onCellClicked: (Int) -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
    ) {
        Log.d("TTT", "MainScreen: gameResult=$gameResult")

        if (!gameResult?.toString().isNullOrEmpty()){
            Text(text = "$gameResult Wins")
        }
        TicTacToeGameBoard(
            cellsData = cellsData,
            onCellClicked = onCellClicked
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    TicTacToeTheme {
        val cells = listOfEmptyCells(DEFAULT_BOARD_CELL_COUNT)
        MainScreenContent(
            cellsData = cells,
            gameResult = Players.X,
            onCellClicked = {}
        )
    }
}