package me.nasrabadiam.tictactoe.di

import me.nasrabadiam.tictactoe.di.scopes.ActivityScope
import me.nasrabadiam.tictactoe.game.GameUseCase
import me.nasrabadiam.tictactoe.mainView
import me.tatarka.inject.annotations.Component

@Component
@ActivityScope
abstract class MainViewComponent(
    @Component val applicationComponent: ApplicationComponent
) {
    abstract val gameUseCase: GameUseCase

    abstract val mainViewFactory: mainView
}
