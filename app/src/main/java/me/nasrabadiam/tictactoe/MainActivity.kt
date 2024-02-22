package me.nasrabadiam.tictactoe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import me.nasrabadiam.tictactoe.game.DEFAULT_BOARD_SIZE
import me.nasrabadiam.tictactoe.game.GameUseCase
import me.nasrabadiam.tictactoe.game.TicTacToeGameBoard
import me.nasrabadiam.tictactoe.ui.theme.TicTacToeTheme
import me.nasrabadiam.tictactoe.game.utlis.listOfEmptyCells

class MainActivity : ComponentActivity() {

    private val gameUseCase = GameUseCase()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            TicTacToeTheme {
                MainScreen(
                    gameUseCase = gameUseCase,
                )
            }
        }
    }
}

@Composable
fun MainScreen(
    gameUseCase: GameUseCase,
    modifier: Modifier = Modifier
) {
    val cells = gameUseCase.cells.collectAsState(
        initial = listOfEmptyCells(DEFAULT_BOARD_SIZE)
    )
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        TicTacToeGameBoard(
            cellsData = cells.value,
            onCellClicked = {
                gameUseCase.onCellClicked(it)
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    TicTacToeTheme {
        MainScreen(GameUseCase())
    }
}