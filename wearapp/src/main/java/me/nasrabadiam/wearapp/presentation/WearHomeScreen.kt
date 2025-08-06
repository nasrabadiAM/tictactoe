package me.nasrabadiam.wearapp.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import me.nasrabadiam.tictactoe.home.GameButtons
import me.nasrabadiam.tictactoe.home.HomeEvent

@Composable
fun WearHomeScreen(
    homeEvent: (HomeEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.padding(12.dp)) {
        Spacer(modifier = modifier.weight(1f))
        GameButtons(
            sendEvent = homeEvent,
            buttonsSpace = { Spacer(modifier = Modifier.weight(0.2f)) })
        Spacer(modifier = Modifier.weight(1f))
    }
}