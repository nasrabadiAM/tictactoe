package me.nasrabadiam.tictactoe

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import me.nasrabadiam.tictactoe.di.InjectApplicationComponent
import me.nasrabadiam.tictactoe.di.InjectMainViewComponent
import me.tatarka.inject.annotations.Inject

@Inject
fun main() = application {
    val applicationComponent = InjectApplicationComponent()

    Window(onCloseRequest = ::exitApplication, title = "TacTrix") {
        val injectMainViewComponent = InjectMainViewComponent(applicationComponent)
        injectMainViewComponent.mainViewFactory().invoke()
    }
}