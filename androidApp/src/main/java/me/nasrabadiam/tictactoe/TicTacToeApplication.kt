package me.nasrabadiam.tictactoe

import android.app.Application

class TicTacToeApplication : Application() {

    val appComponent by lazy(LazyThreadSafetyMode.NONE) {
        ApplicationComponent::class.create(context = this)
    }
}