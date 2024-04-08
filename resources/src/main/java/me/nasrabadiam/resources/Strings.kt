package me.nasrabadiam.resources

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.platform.LocalLayoutDirection
import cafe.adriel.lyricist.LanguageTag
import cafe.adriel.lyricist.Lyricist
import cafe.adriel.lyricist.rememberStrings

public val appStrings: AppLocaleStrings
    @Composable
    @ReadOnlyComposable
    get() = LocalStrings.current

public val LocaleStrings: Map<LanguageTag, AppLocaleStrings> = mapOf(
    "en" to EnAppLocaleStrings,
    "fa" to FaAppLocaleStrings,
)

public val LocalStrings: ProvidableCompositionLocal<AppLocaleStrings> = compositionLocalOf {
    EnAppLocaleStrings
}

@Composable
public fun rememberLocale(
    languageTag: LanguageTag = "en"
): Lyricist<AppLocaleStrings> = rememberStrings(LocaleStrings, languageTag)

@Composable
public fun ProvideLocale(
    lyricist: Lyricist<AppLocaleStrings> = rememberLocale(),
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalStrings provides lyricist.strings,
        LocalLayoutDirection provides lyricist.strings.direction,
        content = content
    )
}