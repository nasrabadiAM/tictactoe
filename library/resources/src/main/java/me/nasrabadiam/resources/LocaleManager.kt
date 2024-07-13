package me.nasrabadiam.resources

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import me.nasrabadiam.resources.Locales.EN

typealias Locale = String

public object LocaleManager {

    var currentLocale: Locale by mutableStateOf(EN.getName())
        private set

    fun updateLocale(locale: Locales) {
        currentLocale = locale.name.lowercase()
    }
}

public fun getLocale(): Locale {
    return LocaleManager.currentLocale
}

public enum class Locales {
    EN,
    FA;
}

public fun Locales.getName(): String {
    return this.name.lowercase()
}