package me.nasrabadiam.wearapp.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.TimeText
import me.nasrabadiam.tictactoe.home.GameButtons
import me.nasrabadiam.tictactoe.home.HomeEvent

@Composable
fun WearHomeScreen(
    homeEvent: (HomeEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        timeText = { TimeText() },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Spacer(modifier = Modifier.weight(1f))
            GameButtons(
                sendEvent = homeEvent,
                buttonsSpace = { Spacer(Modifier.height(4.dp)) },
            )
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}