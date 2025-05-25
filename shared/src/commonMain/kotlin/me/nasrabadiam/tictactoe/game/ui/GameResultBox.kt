package me.nasrabadiam.tictactoe.game.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.layout
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import me.nasrabadiam.tictactoe.game.GameUseCase.Orientation.COLUMN
import me.nasrabadiam.tictactoe.game.GameUseCase.Orientation.CROSS
import me.nasrabadiam.tictactoe.game.GameUseCase.Orientation.ROW
import me.nasrabadiam.tictactoe.game.model.GameResult
import me.nasrabadiam.tictactoe.game.model.GameResult.Draw
import me.nasrabadiam.tictactoe.game.model.GameResult.EndWithWinner
import me.nasrabadiam.tictactoe.game.model.Player.O
import me.nasrabadiam.tictactoe.game.model.Player.X
import me.nasrabadiam.tictactoe.ui.squareWrapContentLayout
import me.nasrabadiam.tictactoe.ui.theme.transparent

@Composable
internal fun GameResultBox(
    gameResult: GameResult,
    backgroundBlur: Dp,
    winnerAnimation: Animatable<Float, AnimationVector1D>,
    onReplayClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {

    val transparent = MaterialTheme.colorScheme.transparent
    val targetColor = MaterialTheme.colorScheme.surface
    val alpha = remember {
        Animatable(
            initialValue = 0f,
        )
    }

    val tweenAnimationSpec = tween<Float>(GAME_RESULT_ANIMATION_DURATION)
    if (!winnerAnimation.isRunning) {
        LaunchedEffect(gameResult) {
            delay(GAME_RESULT_ANIMATION_DELAY)
            alpha.animateTo(
                targetValue = 1f,
                animationSpec = tweenAnimationSpec
            )
        }
    }
    val color = MaterialTheme.colorScheme.onSurface

    val interactionSource = remember { MutableInteractionSource() }

    LaunchedEffect(winnerAnimation) {
        if (gameResult != Draw) {
            winnerAnimation.animateTo(
                targetValue = 1f,
                animationSpec = tween(durationMillis = GAME_RESULT_ANIMATION_DURATION)
            )
        }
    }

    val contentDescription = if (gameResult is EndWithWinner) {
        "${gameResult.player.name} wins this match!"
    } else {
        "Nobody wins this match!"
    }

    Box(
        modifier = modifier
            .squareWrapContentLayout()
            .blur(backgroundBlur)
            .drawBehind {
                val boardPaddings = 16.dp.toPx()
                val cellPaddings = 4.dp.toPx()
                val (start, end) = getWinningLine(
                    gameResult = gameResult,
                    size = size,
                    boardPaddingInPx = boardPaddings,
                    cellPaddingInPx = cellPaddings,
                    winnerAnimationValue = winnerAnimation.value
                )
                val allPaddings = getAllPaddings(boardPaddings, cellPaddings)
                val cellSize = getCellSize(size, allPaddings).width
                val strokeWidth = cellSize * WINNER_LINE_STROKE_FACTOR

                if (start == end) return@drawBehind
                drawLine(
                    color = color,
                    start = start,
                    end = end,
                    cap = StrokeCap.Round,
                    strokeWidth = strokeWidth
                )
            }
            .background(
                Brush.radialGradient(
                    colors = listOf(
                        targetColor.copy(alpha.value),
                        targetColor.copy((alpha.value - 0.2f).coerceIn(0f, 1f)),
                        transparent
                    ),
                )
            )
            .clickable(
                onClickLabel = contentDescription,
                interactionSource = interactionSource,
                indication = null
            ) {
                if (alpha.isRunning) return@clickable
                onReplayClicked.invoke()
            },
    )
    val gameResultState = remember {
        MutableTransitionState(false)
    }
    LaunchedEffect(alpha.value) {
        if (!winnerAnimation.isRunning) {
            gameResultState.targetState = true
        }
    }
    AnimatedVisibility(
        visibleState = gameResultState,
        enter = fadeIn(tweenAnimationSpec) + scaleIn(tweenAnimationSpec)
    ) {
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Box(modifier = Modifier
                .padding(16.dp)
                .layout { measurable, constraints ->
                    // Determine the minimum dimension for square layout
                    val minDimension = minOf(constraints.maxWidth, constraints.maxHeight) / 2
                    val placeable = measurable.measure(
                        constraints.copy(
                            maxWidth = minDimension,
                            minWidth = minDimension,
                            maxHeight = minDimension,
                            minHeight = minDimension,
                        )
                    )
                    layout(minDimension, minDimension) {
                        val topOffset = placeable.height * FIXED_OFFSET_FACTOR
                        placeable.place(x = 0, y = topOffset.toInt())
                    }
                }
            ) {
                val cellColor = MaterialTheme.colorScheme.onSurface
                when (gameResult) {
                    Draw -> {
                        Row(Modifier) {
                            OCell(modifier = Modifier.weight(0.5f), cellColor = cellColor)
                            XCell(modifier = Modifier.weight(0.5f), cellColor = cellColor)
                        }
                    }

                    is EndWithWinner -> {
                        when (gameResult.player) {
                            X -> XCell(cellColor = cellColor)
                            O -> OCell(cellColor = cellColor)
                        }
                    }
                }
            }

            val resultText = when (gameResult) {
                Draw -> "Draw!"
                is EndWithWinner -> "Winner!"
            }

            Text(
                text = resultText,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
                    .padding(4.dp)
            )
            Button(
                onClick = onReplayClicked,
                modifier = Modifier.padding(12.dp)
            ) {
                Text("Again")
            }
        }
    }
}

private fun getWinningLine(
    gameResult: GameResult,
    boardPaddingInPx: Float,
    size: Size,
    cellPaddingInPx: Float,
    winnerAnimationValue: Float
): Pair<Offset, Offset> {
    val allPaddings = getAllPaddings(boardPaddingInPx, cellPaddingInPx)
    val cellSize = getCellSize(size, allPaddings)

    return if (gameResult is EndWithWinner) {
        val winningIndex = gameResult.winningIndex
        val orientation = gameResult.winningOrientation
        val fixedOffset = getFixedOffset(boardPaddingInPx, cellSize.width)

        val cellPaddings = (2 * (winningIndex + 1) - 1) * cellPaddingInPx
        val offset = cellSize.width / 2f + cellPaddings + boardPaddingInPx
        when (orientation) {
            CROSS -> getCrossWinningAnimation(
                gameResult = gameResult,
                fixedOffset = fixedOffset,
                boardSize = size,
                winnerAnimationValue = winnerAnimationValue
            )

            ROW -> getRowWinningAnimation(
                gameResult = gameResult,
                offset = offset,
                fixedOffset = fixedOffset,
                boardSize = size,
                cellSize = cellSize,
                paddingInPx = boardPaddingInPx,
                cellPaddingInPx = cellPaddingInPx,
                winnerAnimationValue = winnerAnimationValue
            )

            COLUMN -> getColumnWinningAnimation(
                gameResult = gameResult,
                offset = offset,
                fixedOffset = fixedOffset,
                boardSize = size,
                cellSize = cellSize,
                winnerAnimationMultiplier = winnerAnimationValue
            )
        }
    } else {
        Offset(0f, 0f) to Offset(0f, 0f)
    }
}

private fun getCrossWinningAnimation(
    gameResult: EndWithWinner,
    fixedOffset: Float,
    boardSize: Size,
    winnerAnimationValue: Float,
): Pair<Offset, Offset> {
    val startOffset = if (gameResult.winningIndex == 0) {
        Offset(x = fixedOffset, y = fixedOffset)
    } else {
        val x = boardSize.width - fixedOffset
        Offset(x = x, y = fixedOffset)
    }

    val maximumEndY = boardSize.height - fixedOffset
    val maximumEndX = boardSize.width - fixedOffset
    val endOffset = if (gameResult.winningIndex == 0) {
        val x =
            (boardSize.width * winnerAnimationValue).coerceIn(fixedOffset, maximumEndX)
        val y =
            (boardSize.height * winnerAnimationValue).coerceIn(fixedOffset, maximumEndY)
        Offset(x, y)
    } else {
        val x =
            (boardSize.width - (boardSize.width * winnerAnimationValue)).coerceIn(
                fixedOffset,
                maximumEndX
            )
        val y = (boardSize.height * winnerAnimationValue).coerceIn(fixedOffset, maximumEndY)
        Offset(x, y)
    }
    return startOffset to endOffset
}

@Suppress("LongParameterList")
private fun getRowWinningAnimation(
    gameResult: EndWithWinner,
    fixedOffset: Float,
    offset: Float,
    boardSize: Size,
    cellSize: Size,
    paddingInPx: Float,
    cellPaddingInPx: Float,
    winnerAnimationValue: Float,
): Pair<Offset, Offset> {
    val cellPaddings = getCellPaddings(gameResult, cellPaddingInPx)
    val heightFactor = gameResult.winningIndex + HALF_FACTOR

    val startY = (cellSize.height * heightFactor) + cellPaddings + paddingInPx
    val startOffset = Offset(x = fixedOffset, y = startY)

    val endY = (cellSize.height * heightFactor) + cellPaddings + paddingInPx
    val maximumX = boardSize.width - fixedOffset
    val endX = ((boardSize.width) * winnerAnimationValue).coerceIn(offset, maximumX)
    val endOffset = Offset(endX, endY)
    return startOffset to endOffset
}

private fun getColumnWinningAnimation(
    gameResult: EndWithWinner,
    fixedOffset: Float,
    offset: Float,
    boardSize: Size,
    cellSize: Size,
    winnerAnimationMultiplier: Float,
): Pair<Offset, Offset> {
    val multiplier = gameResult.winningIndex

    val startX = (cellSize.width * multiplier) + offset
    val startOffset = Offset(x = startX, y = 0f + fixedOffset)

    val xSize = cellSize.width * multiplier + offset
    val maximumY = boardSize.height - fixedOffset
    val ySize = (boardSize.height * winnerAnimationMultiplier).coerceIn(
        offset,
        maximumY
    )
    val endOffset = Offset(xSize, ySize)
    return startOffset to endOffset
}

private fun getFixedOffset(
    paddingInPx: Float,
    length: Float
) = paddingInPx + length * FIXED_OFFSET_FACTOR

private fun getCellPaddings(
    result: EndWithWinner,
    cellPaddingInPx: Float
) = (SINGLE_COMPONENT_PADDING_COUNT * (result.winningIndex + 1) - 1) * cellPaddingInPx

private fun getAllPaddings(paddingInPx: Float, cellPaddingInPx: Float): Float {
    val boardPaddings = SINGLE_COMPONENT_PADDING_COUNT * paddingInPx
    val cellPaddings = CELL_PADDING_COUNT_IN_BOARD * cellPaddingInPx
    return boardPaddings + cellPaddings
}

private fun getCellSize(
    boardSize: Size,
    paddings: Float,
    boardRowCount: Int = 3
): Size {
    val boardWidth = boardSize.width - paddings
    val boardHeight = boardSize.height - paddings
    return boardSize.copy(boardWidth, boardHeight).div(boardRowCount.toFloat())
}

internal const val GAME_RESULT_ANIMATION_DURATION = 400
internal const val GAME_RESULT_ANIMATION_DELAY = 100L
private const val HALF_FACTOR = 0.5f
private const val FIXED_OFFSET_FACTOR = 0.25f
private const val WINNER_LINE_STROKE_FACTOR = 0.20f
private const val SINGLE_COMPONENT_PADDING_COUNT = 2
private const val CELL_PADDING_COUNT_IN_BOARD = 6