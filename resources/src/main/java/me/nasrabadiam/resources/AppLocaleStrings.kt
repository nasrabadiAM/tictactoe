package me.nasrabadiam.resources

import androidx.compose.ui.unit.LayoutDirection

interface AppLocale {

    val direction: LayoutDirection
}

abstract class AppLocaleStrings(
    val appName: String,
    val askYourQuestion: String,
    val contentDescription: ContentDescription
) : AppLocale

data class ContentDescription(
    val send: String
)