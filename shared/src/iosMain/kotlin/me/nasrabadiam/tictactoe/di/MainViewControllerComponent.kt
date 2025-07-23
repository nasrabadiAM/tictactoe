package me.nasrabadiam.tictactoe.di

import me.nasrabadiam.tictactoe.di.component.DispatcherComponent
import me.nasrabadiam.tictactoe.di.component.GameComponents
import me.nasrabadiam.tictactoe.di.component.createDispatcherComponent
import me.nasrabadiam.tictactoe.di.component.createGameComponents
import me.nasrabadiam.tictactoe.di.scopes.ActivityScope
import me.nasrabadiam.tictactoe.mainViewController
import me.tatarka.inject.annotations.Component

@Component
@ActivityScope
abstract class MainViewControllerComponent(
    @Component val applicationComponent: ApplicationComponent,
    @Component val gameComponents: GameComponents = createGameComponents(),
    @Component val dispatcherComponent: DispatcherComponent = createDispatcherComponent()
) {

    abstract val mainViewControllerFactory: mainViewController
}
