package me.nasrabadiam.tictactoe

import android.app.Application
import me.nasrabadiam.tictactoe.di.ApplicationComponent
import me.nasrabadiam.tictactoe.di.create

class TicTacToeApplication : Application() {

    val appComponent by lazy(LazyThreadSafetyMode.NONE) {
        ApplicationComponent::class.create(context = this)
    }
}