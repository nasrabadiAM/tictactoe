package me.nasrabadiam.wearapp

import android.app.Application
import me.nasrabadiam.tictactoe.ApplicationComponent
import me.nasrabadiam.tictactoe.create

class TacTrixWearApplication : Application() {

    val appComponent by lazy(LazyThreadSafetyMode.NONE) {
        ApplicationComponent::class.create(context = this)
    }
}