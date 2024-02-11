package me.nasrabadiam.tictactoe

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun TicTacToeGameBoard(
    modifier: Modifier = Modifier,
    boardSize: Int = 3
) {
    GameGrid(modifier, boardSize)
}

@Composable
private fun GameGrid(
    modifier: Modifier = Modifier,
    boardSize: Int,
) {
    val lastItemIndex = remember { boardSize - 1 }
    Column(modifier) {
        (0 until boardSize).forEach { index ->
            GameRow(
                modifier = Modifier.wrapContentHeight(),
                rowSize = boardSize
            )
            if (index != lastItemIndex)
                ColumnDivider()
        }
    }
}

@Composable
private fun ColumnDivider() {
    Box(
        modifier = Modifier
            .height(2.dp)
            .width(94.dp)
            .semantics { testTag = "line" }
            .background(MaterialTheme.colorScheme.onBackground)
    )
}

@Composable
private fun GameRow(modifier: Modifier = Modifier, rowSize: Int) {
    val lastItemIndex = remember { rowSize - 1 }
    Row(modifier) {
        (0 until rowSize).forEach { index ->
            GameCell(index, lastItemIndex)
        }
    }
}

@Composable
private fun GameCell(index: Int, lastItemIndex: Int) {
    Text(
        modifier = Modifier
            .padding(8.dp)
            .semantics { testTag = "cell" }, text = "X"
    )
    if (index != lastItemIndex)
        VerticalDivider()
}

@Composable
private fun VerticalDivider() {
    Box(
        modifier = Modifier
            .height(36.dp)
            .width(2.dp)
            .semantics { testTag = "line" }
            .background(MaterialTheme.colorScheme.onBackground)
    )
}


@Preview(showBackground = true)
@Composable
fun TicTacToePreview() {
    TicTacToeGameBoard(
        modifier = Modifier
            .wrapContentSize()
            .padding(8.dp)
    )
}