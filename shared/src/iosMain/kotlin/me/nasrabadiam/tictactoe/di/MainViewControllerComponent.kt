package me.nasrabadiam.tictactoe.di

import me.nasrabadiam.tictactoe.mainViewController
import me.nasrabadiam.tictactoe.di.scopes.ActivityScope
import me.nasrabadiam.tictactoe.game.GameUseCase
import me.tatarka.inject.annotations.Component

@Component
@ActivityScope
abstract class MainViewControllerComponent(
    @Component val applicationComponent: ApplicationComponent
) {
    abstract val gameUseCase: GameUseCase

    abstract val mainViewControllerFactory: mainViewController
}
