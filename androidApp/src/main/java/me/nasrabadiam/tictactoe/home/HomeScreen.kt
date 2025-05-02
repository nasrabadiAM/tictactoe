package me.nasrabadiam.tictactoe.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import me.nasrabadiam.tictactoe.R
import me.nasrabadiam.tictactoe.home.HomeEvent.PlayWithAFriend
import me.nasrabadiam.tictactoe.ui.GameWindowSizeClass
import me.nasrabadiam.tictactoe.ui.GameWindowSizeClass.COMPACT
import me.nasrabadiam.tictactoe.ui.GameWindowSizeClass.EXPANDED
import me.nasrabadiam.tictactoe.ui.GameWindowSizeClass.NORMAL
import me.nasrabadiam.tictactoe.ui.WindowScreenSizeDataProvider
import me.nasrabadiam.tictactoe.ui.icon.Group
import me.nasrabadiam.tictactoe.ui.theme.TicTacToeTheme

@Composable
fun HomeScreen(
    homeEvent: (HomeEvent) -> Unit,
    windowSizeClass: GameWindowSizeClass,
) {
    TicTacToeTheme {
        HomeScreenContent(
            sendEvent = homeEvent,
            windowSizeClass = windowSizeClass,
        )
    }
}

@Composable
fun HomeScreenContent(
    modifier: Modifier = Modifier,
    windowSizeClass: GameWindowSizeClass,
    sendEvent: (HomeEvent) -> Unit
) {
    when (windowSizeClass) {
        COMPACT, NORMAL -> VerticalHomeScreen(modifier, sendEvent)
        EXPANDED -> HorizontalHomeScreen(modifier, sendEvent)
    }
}

@Composable
private fun VerticalHomeScreen(
    modifier: Modifier,
    sendEvent: (HomeEvent) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceDim),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))
        AppLogo(modifier = Modifier.wrapContentSize())
        Spacer(modifier = Modifier.weight(1f))
        GameButtons(
            sendEvent = sendEvent,
            buttonsSpace = { Spacer(modifier = Modifier.weight(0.2f)) })
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
private fun HorizontalHomeScreen(
    modifier: Modifier,
    sendEvent: (HomeEvent) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceDim),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.weight(0.2f))
        AppLogo(modifier.weight(1f))
        Spacer(modifier = Modifier.weight(0.2f))
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            GameButtons(
                sendEvent = sendEvent,
                buttonsSpace = { Spacer(modifier = Modifier.height(16.dp)) })
        }
        Spacer(modifier = Modifier.weight(0.2f))
    }
}

@Composable
private fun AppLogo(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .semantics { contentDescription = "App Logo" }
            .padding(60.dp)
            .clip(MaterialTheme.shapes.large)
            .scale(1.5f)
            .background(MaterialTheme.colorScheme.primary)
    ) {
        Image(
            modifier = Modifier.semantics(mergeDescendants = true) {},
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentScale = ContentScale.FillWidth,
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimary),
            contentDescription = null
        )
    }
}

@Composable
private fun GameButtons(
    modifier: Modifier = Modifier,
    sendEvent: (HomeEvent) -> Unit,
    buttonsSpace: @Composable () -> Unit
) {
    Button(
        modifier = modifier
            .semantics { contentDescription = PLAY_WITH_A_FRIEND_TEXT }
            .widthIn(220.dp),
        colors = ButtonDefaults.buttonColors().copy(
            contentColor = MaterialTheme.colorScheme.onPrimary,
            containerColor = MaterialTheme.colorScheme.primary
        ),
        onClick = {
            sendEvent.invoke(PlayWithAFriend)
        }
    ) {
        Icon(
            modifier = Modifier.semantics(mergeDescendants = true) {},
            imageVector = Icons.Rounded.Group,
            tint = MaterialTheme.colorScheme.onPrimary,
            contentDescription = null
        )
        Text(
            modifier = Modifier.padding(start = 8.dp),
            text = PLAY_WITH_A_FRIEND_TEXT
        )
    }
    buttonsSpace()
    Button(
        modifier = modifier
            .semantics { contentDescription = PLAY_SOLO_TEXT + COMING_SOON_TEXT }
            .widthIn(min = 220.dp),
        onClick = { TODO("Not Implemented yet!") },
        enabled = false
    ) {
        Icon(
            modifier = Modifier.semantics(mergeDescendants = true) {},
            imageVector = Icons.Rounded.Person,
            contentDescription = null
        )
        Text(
            modifier = Modifier.padding(start = 8.dp),
            text = PLAY_SOLO_TEXT
        )
        Text(
            modifier = Modifier.padding(start = 2.dp),
            text = COMING_SOON_TEXT,
            style = MaterialTheme.typography.labelSmall
        )
    }
}

private const val PLAY_SOLO_TEXT = "Play solo"
private const val COMING_SOON_TEXT = "(coming soon)"
private const val PLAY_WITH_A_FRIEND_TEXT = "Play with a friend"

@Preview(showBackground = true)
@Preview(showSystemUi = true, device = Devices.TABLET)
@Composable
fun HomeScreenPreView(
    @PreviewParameter(WindowScreenSizeDataProvider::class)
    windowSizeClass: GameWindowSizeClass
) {
    TicTacToeTheme {
        HomeScreenContent(windowSizeClass = windowSizeClass, sendEvent = {})
    }
}