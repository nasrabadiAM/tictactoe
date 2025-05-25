package me.nasrabadiam.tictactoe

import androidx.compose.runtime.Composable
import me.tatarka.inject.annotations.Inject

typealias MainView = @Composable () -> Unit

@Inject
@Composable
fun MainView(app: App) {
    app()
}