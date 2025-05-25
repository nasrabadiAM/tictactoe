package me.nasrabadiam.tictactoe.game.ui

import me.nasrabadiam.tictactoe.GameWindowSizeClass
import org.jetbrains.compose.ui.tooling.preview.PreviewParameterProvider

class WindowScreenSizeDataProvider : PreviewParameterProvider<GameWindowSizeClass> {

    override val values: Sequence<GameWindowSizeClass>
        get() {
            return sequenceOf(
                GameWindowSizeClass.NORMAL,
                GameWindowSizeClass.COMPACT,
                GameWindowSizeClass.EXPANDED,
            )
        }
}