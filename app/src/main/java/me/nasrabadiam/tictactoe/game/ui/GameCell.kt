package me.nasrabadiam.tictactoe.game.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import me.nasrabadiam.tictactoe.game.model.Cell
import me.nasrabadiam.tictactoe.game.model.Player.O
import me.nasrabadiam.tictactoe.game.model.Player.X
import me.nasrabadiam.tictactoe.ui.squareWrapContentLayout

@Composable
internal fun GameCell(
    cell: Cell,
    onClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .padding(4.dp)
            .shadow(
                elevation = 2.dp,
                shape = MaterialTheme.shapes.large
            )
            .aspectRatio(1f)
            .clip(MaterialTheme.shapes.large)
            .background(MaterialTheme.colorScheme.primary)
            .clickable { onClick.invoke(cell.index) }
            .semantics { testTag = "cell_${cell.index}_${cell.value}" },
    ) {
        AnimatedVisibility(
            visible = cell.value != null,
            enter = fadeIn() + scaleIn()
        ) {
            if (cell.value == X) {
                XCell()
            } else if (cell.value == O) {
                OCell()
            }
        }
    }
}

@Composable
internal fun XCell(
    modifier: Modifier = Modifier,
    cellColor: Color = MaterialTheme.colorScheme.onPrimary
) {
    Box(
        modifier
            .testTag("X")
            .squareWrapContentLayout()
            .drawBehind {
                val size = minOf(size.height, size.width)
                val paddingInPx = size.cellPadding()

                val endX = size - paddingInPx
                val endY = size - paddingInPx

                val strokeWidth = strokeWidth(size, paddingInPx)

                drawLine(
                    color = cellColor,
                    start = Offset(paddingInPx, paddingInPx),
                    end = Offset(endX, endY),
                    strokeWidth = strokeWidth,
                    cap = StrokeCap.Round
                )
                drawLine(
                    color = cellColor,
                    start = Offset(paddingInPx, endY),
                    end = Offset(endX, paddingInPx),
                    strokeWidth = strokeWidth,
                    cap = StrokeCap.Round
                )
            })
}

@Composable
internal fun OCell(
    modifier: Modifier = Modifier,
    cellColor: Color = MaterialTheme.colorScheme.onPrimary
) {
    Box(
        modifier
            .testTag("O")
            .squareWrapContentLayout()
            .drawBehind {
                val size = minOf(size.height, size.width)

                val paddingInPx = size.cellPadding()
                val radius = (size - (paddingInPx * 2)) / 2
                val strokeWidth = strokeWidth(size, paddingInPx)
                val center = Offset(paddingInPx + radius, paddingInPx + radius)
                drawCircle(
                    color = cellColor,
                    radius = radius,
                    center = center,
                    style = Stroke(width = strokeWidth)
                )
            })
}

private const val CELL_PADDING_DIVIDER = 4

private fun Float.cellPadding() = this / CELL_PADDING_DIVIDER

private const val STROKE_WIDTH_DIVIDER = 3

private fun strokeWidth(
    size: Float,
    paddingInPx: Float
) = (size - (paddingInPx + paddingInPx)) / STROKE_WIDTH_DIVIDER

@Composable
@Preview
fun GameCellPreview(
    @PreviewParameter(GameCellDataProvider::class) cellData: Cell
) {
    GameCell(cell = cellData, onClick = {})
}

class GameCellDataProvider : PreviewParameterProvider<Cell> {

    override val values: Sequence<Cell>
        get() {
            return sequenceOf(Cell(0, X), Cell(1, O))
        }
}