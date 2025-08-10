package me.nasrabadiam.tictactoe.di.component

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import me.nasrabadiam.tictactoe.di.Named
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.KmpComponentCreate
import me.tatarka.inject.annotations.Provides

@Component
abstract class DispatcherComponent {

    @Provides
    fun defaultDispatcher():
        @Named("default")
        CoroutineDispatcher = Dispatchers.Default

    @Provides
    fun mainDispatcher():
        @Named("main")
        CoroutineDispatcher = Dispatchers.Main
}

@KmpComponentCreate
expect fun createDispatcherComponent(): DispatcherComponent