package me.nasrabadiam.tictactoe.game.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
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
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.constrainHeight
import androidx.compose.ui.unit.dp
import me.nasrabadiam.tictactoe.GameWindowSizeClass
import me.nasrabadiam.tictactoe.GameWindowSizeClass.COMPACT
import me.nasrabadiam.tictactoe.GameWindowSizeClass.EXPANDED
import me.nasrabadiam.tictactoe.GameWindowSizeClass.NORMAL
import me.nasrabadiam.tictactoe.game.ui.GameEvent.CellClicked
import me.nasrabadiam.tictactoe.game.ui.GameEvent.RestartClicked
import me.nasrabadiam.tictactoe.game.ui.GameEvent.RulesClicked
import me.nasrabadiam.tictactoe.ui.theme.TicTacToeTheme
import kotlin.math.max

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
            .background(MaterialTheme.colorScheme.surfaceDim),
    ) {
        ScoresSection(
            state.scores, isExpandedScreen
        )

        TicTacToeGameBoard(
            cellsData = state.cells, onCellClicked = { index ->
                sendEvent(CellClicked(index))
            }, modifier = Modifier
                .weight(1f)
                .padding(8.dp)
        )
        ButtonsSection(
            sendEvent, isExpandedScreen
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
            .background(MaterialTheme.colorScheme.surfaceDim),
    ) {
        ScoresSection(
            state.scores, isExpandedScreen
        )
        TicTacToeGameBoard(
            cellsData = state.cells, onCellClicked = { index ->
                sendEvent(CellClicked(index))
            }, modifier = Modifier
                .weight(1f)
                .padding(8.dp)
        )
        ButtonsSection(
            sendEvent, isExpandedScreen
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
            .background(MaterialTheme.colorScheme.surfaceDim),
    ) {
        Column {
            // TODO:  Scores
            ActionButtons(onRestartClicked = {
                sendEvent(RestartClicked)
            }, onRulesClicked = {
                sendEvent(RulesClicked)
            })
        }
        TicTacToeGameBoard(
            cellsData = state.cells, onCellClicked = { index ->
                sendEvent(CellClicked(index))
            }, modifier = Modifier
                .weight(1f)
                .padding(8.dp)
        )
    }
}

@Composable
private fun ButtonsSection(
    sendEvent: (GameEvent) -> Unit,
    isExpandedScreen: Boolean,
    modifier: Modifier = Modifier,
) {
    if (isExpandedScreen) {
        Column(
            modifier = modifier.padding(
                vertical = 8.dp, horizontal = 32.dp
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
    } else {
        Column(
            modifier = modifier.padding(
                vertical = 32.dp, horizontal = 8.dp
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
}

@Composable
private fun ActionButtons(
    onRestartClicked: () -> Unit,
    onRulesClicked: () -> Unit,
) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .widthIn(
                min = 124.dp, max = 210.dp
            ), onClick = onRestartClicked
    ) {
        Icon(imageVector = Icons.Rounded.Refresh, contentDescription = null)
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = "Restart")
    }
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .widthIn(
                min = 124.dp, max = 210.dp
            ), enabled = false, onClick = onRulesClicked
    ) {
        Text(text = "Game Rules")
        Text(
            text = "(Coming Soon)",
            style = MaterialTheme.typography.labelSmall
        )
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
                    vertical = 8.dp, horizontal = 36.dp
                )
        ) {
            ScoreContainer(score = scores.xScore) {
                XCell(
                    modifier = Modifier
                        .widthIn(min = 20.dp)
                        .weight(1f)
                        .padding(horizontal = 8.dp),
                    cellColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            ScoreContainer(score = scores.oScore, isMirrored = true) {
                OCell(
                    modifier = Modifier
                        .widthIn(min = 20.dp)
                        .weight(1f)
                        .padding(horizontal = 8.dp),
                    cellColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        }
    } else {
        ScoresCoordinator(modifier) {
            ScoreContainer(score = scores.xScore, playerIcon = {
                XCell(
                    modifier = Modifier
                        .widthIn(min = 20.dp)
                        .padding(horizontal = 8.dp),
                    cellColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            })
            ScoreContainer(score = scores.oScore, isMirrored = true, playerIcon = {
                OCell(
                    modifier = Modifier
                        .background(Color.Blue)
                        .widthIn(min = 20.dp)
                        .padding(horizontal = 8.dp),
                    cellColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            })
        }
    }
}

@Composable
private fun ScoresCoordinator(
    modifier: Modifier = Modifier,
    topPadding: Dp = 12.dp,
    bottomPadding: Dp = 12.dp,
    startPadding: Dp = 8.dp,
    endPadding: Dp = 8.dp,
    itemMaxWidth: Dp = 200.dp,
    content: @Composable () -> Unit
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

        val itemHeight = outerConstraints.maxHeight - topPaddingPx - bottomPaddingPx
        val itemConstraints = outerConstraints.copy(
            maxWidth = itemWidth,
            maxHeight = itemHeight
        )

        // Measure elements with their maximum width and keep track of the height
        var layoutHeight = topPaddingPx + bottomPaddingPx
        val placeables = measurables.mapIndexed { _, measurable ->
            val placeable = measurable.measure(itemConstraints)
            layoutHeight += placeable.height
            placeable
        }

        layout(
            width = outerConstraints.maxWidth,
            height = outerConstraints.constrainHeight(layoutHeight)
        ) {
            var xPosition = startPaddingPx

            placeables.forEachIndexed { _, placeable ->
                placeable.placeRelative(x = xPosition, y = topPaddingPx)
                val space = calculateSpaceWidth(
                    outerConstraints,
                    itemCounts,
                    itemWidth,
                    startPaddingPx,
                    endPaddingPx
                )
                val spaceWidth = max(minMiddleSpace, space)
                xPosition += itemWidth + spaceWidth
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

@Composable
private fun ScoreContainer(
    score: Int,
    modifier: Modifier = Modifier,
    isMirrored: Boolean = false,
    playerIcon: @Composable RowScope.() -> Unit
) {
    val xScale = if (!isMirrored) -1f else 1f
    Row(
        modifier = modifier
            .scale(scaleX = xScale, scaleY = 1f)
            .widthIn(max = 200.dp)
            .aspectRatio(3f / 2f)
            .shadow(8.dp, MaterialTheme.shapes.large)
            .background(
                MaterialTheme.colorScheme.primaryContainer, MaterialTheme.shapes.large
            )
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.surface,
                shape = MaterialTheme.shapes.large
            ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        ScoreText(score = score)
        playerIcon()
    }
}

@Composable
private fun ScoreText(score: Int, modifier: Modifier = Modifier) {
    Spacer(modifier = Modifier.width(12.dp))
    Text(
        modifier = modifier.wrapContentSize().widthIn(min = 24.dp),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.titleLarge,
        text = "$score",
        color = MaterialTheme.colorScheme.onPrimaryContainer
    )
    Spacer(modifier = Modifier.width(12.dp))
}

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