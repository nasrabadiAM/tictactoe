package me.nasrabadiam.tictactoe.strings

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.intl.Locale
import me.nasrabadiam.resources.LanguageTag
import me.nasrabadiam.resources.LayoutDirection
import me.nasrabadiam.resources.Locales
import me.nasrabadiam.resources.Lyricist
import me.nasrabadiam.resources.ProvideStrings
import me.nasrabadiam.resources.getName
import me.nasrabadiam.resources.rememberStrings

internal val AppStrings: Map<LanguageTag, Strings> = mapOf(
    Locales.EN.getName() to EnStrings,
    Locales.FA.getName() to FaStrings,
)

internal val AppStringsLayoutDirections: Map<LanguageTag, LayoutDirection> = mapOf(
    Locales.EN.getName() to LayoutDirection.LTR,
    Locales.FA.getName() to LayoutDirection.RTL,
)

internal val LocalStrings: ProvidableCompositionLocal<Strings> =
    staticCompositionLocalOf { EnStrings }

internal val appStrings: Strings
    @Composable
    get() = LocalStrings.current

internal fun getAppStrings(locale: Locale = Locale.current): Strings {
    return (AppStrings[locale.toLanguageTag()] ?: EnStrings)
}

@Composable
internal fun rememberAppStrings(
    defaultLanguageTag: LanguageTag = Locales.EN.getName(),
    currentLanguageTag: LanguageTag = Locale.current.toLanguageTag(),
): Lyricist<Strings> =
    rememberStrings(
        AppStrings,
        AppStringsLayoutDirections,
        defaultLanguageTag,
        currentLanguageTag
    )

@Composable
internal fun ProvideAppStrings(
    lyricist: Lyricist<Strings> = rememberAppStrings(),
    content: @Composable () -> Unit
) {
    ProvideStrings(lyricist, LocalStrings, content)
}