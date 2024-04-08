package me.nasrabadiam.resources

import androidx.compose.ui.unit.LayoutDirection

val FaAppLocaleStrings = object : AppLocaleStrings(
    appName = "دستیار چت جی‌پی‌تی",
    askYourQuestion = "سوال بپرس...",
    contentDescription = ContentDescription(
        send = "ارسال"
    )
) {
    override val direction = LayoutDirection.Rtl
}