package me.nasrabadiam.tictactoe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import me.nasrabadiam.tictactoe.game.GameUseCase

class MainActivity : ComponentActivity() {

    private val gameUseCase = GameUseCase()

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val widthSizeClass = calculateWindowSizeClass(this).widthSizeClass
            val isExpandedScreen = widthSizeClass == WindowWidthSizeClass.Expanded

            MainScreen(gameUseCase, isExpandedScreen)
        }
    }
}