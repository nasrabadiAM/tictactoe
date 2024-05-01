package me.nasrabadiam.tictactoe.game.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import me.nasrabadiam.tictactoe.GameWindowSizeClass
import me.nasrabadiam.tictactoe.game.model.Player
import me.nasrabadiam.tictactoe.game.model.Player.O
import me.nasrabadiam.tictactoe.game.model.Player.X
import kotlin.math.max

@Composable
internal fun ScoresSection(
    scores: ScoresState,
    currentPlayer: Player,
    modifier: Modifier = Modifier
) {
    ScoresCoordinator(modifier.heightIn(max = 360.dp, min = 80.dp)) {
        val cellColor = MaterialTheme.colorScheme.onSecondaryContainer
        ScoreContainer(
            score = scores.xScore,
            isTurnEnable = currentPlayer == X,
            player = X,
            playerIcon = {
                XCell(
                    modifier = Modifier
                        .widthIn(min = 20.dp)
                        .padding(horizontal = 8.dp),
                    cellColor = cellColor
                )
            }
        )
        ScoreContainer(
            score = scores.oScore,
            player = O,
            isTurnEnable = currentPlayer == O,
            playerIcon = {
                OCell(
                    modifier = Modifier
                        .widthIn(min = 20.dp)
                        .padding(horizontal = 8.dp),
                    cellColor = cellColor
                )
            }
        )
    }
}

@Composable
private fun ScoresCoordinator(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    BoxWithConstraints {
        if (maxWidth > maxHeight) {
            ScoresCoordinatorColumn(
                modifier = modifier,
                content = content,
            )
        } else {
            ScoresCoordinatorRow(
                modifier = modifier,
                content = content,
            )
        }
    }
}

@Composable
private fun ScoresCoordinatorRow(
    modifier: Modifier,
    content: @Composable () -> Unit,
    topPadding: Dp = 12.dp,
    bottomPadding: Dp = 12.dp,
    startPadding: Dp = 12.dp,
    endPadding: Dp = 12.dp,
    itemMaxWidth: Dp = 200.dp,
) {
    Layout(modifier = modifier, content = content) { measurables, outerConstraints ->
        val topPaddingPx = topPadding.roundToPx()
        val bottomPaddingPx = bottomPadding.roundToPx()
        val startPaddingPx = startPadding.roundToPx()
        val endPaddingPx = endPadding.roundToPx()
        val itemMaxWidthPx = itemMaxWidth.roundToPx()

        val itemCounts = measurables.size
        val minMiddleSpace = outerConstraints.maxWidth / 5
        val maxWidthWithSpaces =
            outerConstraints.maxWidth - minMiddleSpace - startPaddingPx - endPaddingPx
        val itemWidth = (maxWidthWithSpaces / itemCounts).coerceIn(0, itemMaxWidthPx)

        val itemHeight = (itemWidth * 2 / 3)
        val itemConstraints = outerConstraints.copy(
            maxWidth = itemWidth,
            maxHeight = itemHeight,
            minHeight = itemHeight
        )

        // Measure elements with their maximum width and keep track of the height
        var itemHeightCalc = 0
        val placeables = measurables.mapIndexed { _, measurable ->
            val placeable = measurable.measure(itemConstraints)
            itemHeightCalc = placeable.height
            placeable
        }
        val layoutHeight = topPaddingPx + bottomPaddingPx + itemHeightCalc
        layout(
            width = outerConstraints.maxWidth,
            height = layoutHeight
        ) {
            var xPosition = startPaddingPx

            placeables.forEachIndexed { index, placeable ->
                placeable.placeRelative(x = xPosition, y = topPaddingPx)
                val space = calculateSpaceWidth(
                    outerConstraints, itemCounts, itemWidth, startPaddingPx, endPaddingPx
                )
                val spaceWidth = max(minMiddleSpace, space)
                xPosition += itemWidth + spaceWidth
            }
        }
    }
}

@Composable
private fun ScoresCoordinatorColumn(
    modifier: Modifier,
    content: @Composable () -> Unit,
    topPadding: Dp = 24.dp,
    bottomPadding: Dp = 24.dp,
    startPadding: Dp = 12.dp,
    endPadding: Dp = 12.dp,
    itemMaxHeight: Dp = 136.dp,
) {
    Layout(modifier = modifier, content = content) { measurables, outerConstraints ->
        val maxHeightPx = outerConstraints.maxHeight
        val topPaddingPx = topPadding.roundToPx()
        val bottomPaddingPx = bottomPadding.roundToPx()
        val startPaddingPx = startPadding.roundToPx()
        val endPaddingPx = endPadding.roundToPx()
        val itemMaxHeightPx = itemMaxHeight.roundToPx()

        val itemCounts = measurables.size
        val minMiddleSpace = maxHeightPx / 5

        val maxHeightWithoutSpaces =
            maxHeightPx - minMiddleSpace - topPaddingPx - bottomPaddingPx

        val itemHeight = (maxHeightWithoutSpaces / itemCounts).coerceIn(0, itemMaxHeightPx)

        val itemWidth = itemHeight * 3 / 2
        val itemConstraints = outerConstraints.copy(
            maxWidth = itemWidth,
            maxHeight = itemHeight,
            minHeight = itemHeight
        )

        // Measure elements with their maximum width and keep track of the height
        val placeables = measurables.map { measurable ->
            measurable.measure(itemConstraints)
        }

        val layoutHeight =
            topPaddingPx + bottomPaddingPx + (itemHeight * itemCounts) + minMiddleSpace
        val layoutWidth =
            startPaddingPx + endPaddingPx + itemWidth
        layout(
            width = layoutWidth,
            height = layoutHeight
        ) {
            var yPosition = topPaddingPx

            placeables.forEachIndexed { _, placeable ->

                placeable.placeRelative(x = startPaddingPx, y = yPosition)
                val space = calculateSpaceHeight(
                    layoutHeight,
                    itemCounts,
                    itemHeight,
                    topPaddingPx,
                    bottomPaddingPx
                )
                val spaceHeight = max(minMiddleSpace, space)
                yPosition += itemHeight + spaceHeight
            }
        }
    }
}

private fun calculateSpaceWidth(
    outerConstraints: Constraints,
    itemCounts: Int,
    itemWidth: Int,
    startPaddingPx: Int,
    endPaddingPx: Int
) = outerConstraints.maxWidth - ((itemCounts * itemWidth) + startPaddingPx + endPaddingPx)

private fun calculateSpaceHeight(
    maxHeight: Int,
    itemCounts: Int,
    itemHeight: Int,
    topPaddingPx: Int,
    bottomPaddingPx: Int
) = maxHeight - ((itemCounts * itemHeight) + topPaddingPx + bottomPaddingPx)

@Composable
private fun ScoreContainer(
    score: Int,
    player: Player,
    isTurnEnable: Boolean,
    modifier: Modifier = Modifier,
    isMirrored: Boolean = false,
    playerIcon: @Composable RowScope.() -> Unit
) {
    val xScale = if (!isMirrored) -1f else 1f

    val turnModifier = if (isTurnEnable) {
        Modifier.border(
            width = 1.dp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            shape = MaterialTheme.shapes.large
        )
    } else {
        Modifier.border(
            width = 1.dp,
            color = MaterialTheme.colorScheme.surface,
            shape = MaterialTheme.shapes.large
        )
    }

    Row(
        modifier = modifier
            .scale(scaleX = xScale, scaleY = 1f)
            .widthIn(max = 200.dp, min = 60.dp)
            .aspectRatio(3f / 2f)
            .shadow(8.dp, MaterialTheme.shapes.large)
            .background(
                MaterialTheme.colorScheme.secondaryContainer, MaterialTheme.shapes.large
            )
            .then(turnModifier)
            .semantics { contentDescription = "${player.name} Score is $score" },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        ScoreText(
            score = score,
            modifier = Modifier.scale(scaleX = xScale, scaleY = 1f)
        )
        playerIcon()
    }
}

@Composable
private fun ScoreText(score: Int, modifier: Modifier = Modifier) {
    Spacer(modifier = Modifier.width(12.dp))
    Text(
        modifier = modifier
            .wrapContentSize()
            .widthIn(min = 24.dp),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.titleLarge,
        text = "$score",
        color = MaterialTheme.colorScheme.onSecondaryContainer
    )
    Spacer(modifier = Modifier.width(12.dp))
}

@Preview(showSystemUi = true)
@Preview(showSystemUi = true, device = Devices.TABLET)
@Composable
fun ScoreSectionPreview(
    @PreviewParameter(WindowScreenSizeDataProvider::class) windowSizeClass: GameWindowSizeClass

) {
    ScoresSection(
        scores = ScoresState(1, 2, 3),
        currentPlayer = Player.X
    )
}