package me.nasrabadiam.resources

import androidx.compose.ui.unit.LayoutDirection

val EnAppLocaleStrings = object : AppLocaleStrings(
    appName = "Chat GPT Assistant",
    askYourQuestion = "Ask Your Question...",
    contentDescription = ContentDescription(
        send = "Send"
    )
) {
    override val direction = LayoutDirection.Ltr
}