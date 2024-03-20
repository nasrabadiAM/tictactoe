package me.nasrabadiam.tictactoe.di

import android.content.Context
import me.nasrabadiam.tictactoe.game.GameUseCase
import me.nasrabadiam.tictactoe.di.scopes.AppScope
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides

@AppScope
@Component
abstract class ApplicationComponent(@get:Provides val context: Context) {

    abstract val gameUseCase: GameUseCase

    companion object
}
