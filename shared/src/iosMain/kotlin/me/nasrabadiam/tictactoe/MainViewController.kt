package me.nasrabadiam.tictactoe

import androidx.compose.ui.window.ComposeUIViewController
import me.tatarka.inject.annotations.Inject
import platform.UIKit.UIViewController

typealias mainViewController = () -> UIViewController

@Inject
fun mainViewController(app: App): UIViewController {
    return ComposeUIViewController(
        configure = {
            enforceStrictPlistSanityCheck = false
        }
    ) {
        app()
    }
}