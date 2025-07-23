package me.nasrabadiam.tictactoe.di.component

import me.nasrabadiam.tictactoe.di.Named
import me.nasrabadiam.tictactoe.di.scopes.AppScope
import me.nasrabadiam.tictactoe.game.GameUseCase
import me.nasrabadiam.tictactoe.game.model.DEFAULT_BOARD_CELL_COUNT
import me.nasrabadiam.tictactoe.game.model.Player
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.KmpComponentCreate
import me.tatarka.inject.annotations.Provides

@Component
@AppScope
abstract class GameComponents(
    @Component val dispatcherComponent: DispatcherComponent = createDispatcherComponent()
) {

    @get:Provides
    val starterPlayer: Player = Player.X

    @get:Provides
    @get:Named("boardCellCount")
    val boardCellCount: Int = DEFAULT_BOARD_CELL_COUNT

    abstract val gameUseCase: GameUseCase
}

@KmpComponentCreate
expect fun createGameComponents(): GameComponents