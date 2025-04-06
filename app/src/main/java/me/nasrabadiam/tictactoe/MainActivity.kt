package me.nasrabadiam.tictactoe

import android.app.Activity
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import ir.metrix.attribution.MetrixAttribution
import ir.metrix.attribution.OnDeeplinkResponseListener
import me.nasrabadiam.tictactoe.di.ApplicationComponent
import me.nasrabadiam.tictactoe.di.scopes.ActivityScope
import me.nasrabadiam.tictactoe.game.GameUseCase
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MetrixAttribution.setOnDeeplinkResponseListener(object : OnDeeplinkResponseListener {
            override fun launchReceivedDeeplink(deeplink: Uri): Boolean {
                /*return if (shouldMetrixSdkLaunchTheDeeplink(deeplink)) {
                    true
                } else {
                    false
                }*/
                return true
            }
        })

        val activityComponent = ActivityComponent::class.create(activity = this)
        setContent { activityComponent.app() }
    }
}

fun ApplicationComponent.Companion.from(activity: Activity): ApplicationComponent =
    (activity.applicationContext as TicTacToeApplication).appComponent

@Component
@ActivityScope
abstract class ActivityComponent(
    @get:Provides val activity: ComponentActivity,
    @Component val applicationComponent: ApplicationComponent = ApplicationComponent.from(activity)
) {

    abstract val gameUseCase: GameUseCase

    abstract val app: App
}