package me.nasrabadiam.tictactoe

import me.nasrabadiam.tictactoe.di.InjectApplicationComponent
import me.nasrabadiam.tictactoe.di.InjectMainViewComponent

fun main() {
    val applicationComponent = InjectApplicationComponent()
    val injectMainViewComponent = InjectMainViewComponent(applicationComponent)
    injectMainViewComponent.mainViewFactory()
}
