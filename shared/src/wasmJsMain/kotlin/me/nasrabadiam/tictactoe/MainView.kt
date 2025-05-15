package me.nasrabadiam.tictactoe

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import kotlinx.browser.document
import me.tatarka.inject.annotations.Inject

typealias mainView = () -> Unit

@OptIn(ExperimentalComposeUiApi::class)
@Inject
fun mainView(app: App) {
    val body = requireNotNull(document.body)
    ComposeViewport(body) {
        app()
    }
}