package me.nasrabadiam.wearapp.presentation

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import me.nasrabadiam.tictactoe.ApplicationComponent
import me.nasrabadiam.tictactoe.create
import me.nasrabadiam.tictactoe.di.component.GameComponents
import me.nasrabadiam.tictactoe.di.component.createGameComponents
import me.nasrabadiam.tictactoe.di.scopes.ActivityScope
import me.nasrabadiam.tictactoe.game.GameUseCase
import me.nasrabadiam.wearapp.TacTrixWearApplication
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

        super.onCreate(savedInstanceState)

        setTheme(android.R.style.Theme_DeviceDefault)
        enableEdgeToEdge()
        val activityComponent = WearMainActivityComponent::class.create(activity = this)
        setContent { activityComponent.app() }
    }
}

fun ApplicationComponent.Companion.from(activity: Activity): ApplicationComponent =
    (activity.applicationContext as TacTrixWearApplication).appComponent

@Component
@ActivityScope
abstract class WearMainActivityComponent(
    @get:Provides val activity: ComponentActivity,
    @Component val gameComponents: GameComponents = createGameComponents(),
    @Component val applicationComponent: ApplicationComponent = ApplicationComponent.from(activity)
) {

    abstract val gameUseCase: GameUseCase

    abstract val app: WearApp
}