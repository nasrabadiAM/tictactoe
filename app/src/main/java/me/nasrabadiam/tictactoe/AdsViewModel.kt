package me.nasrabadiam.tictactoe

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import me.tatarka.inject.annotations.Inject

@Inject
class AdsViewModel : ViewModel() {
    private val _showAds = MutableStateFlow(false)
    val showAdsState = _showAds.asStateFlow()

    private val _continueApp = MutableStateFlow(false)
    val continueApp = _continueApp.asStateFlow()

    fun showAd() {
        _continueApp.update { false }
        _showAds.update { true }
    }

    fun adShown() {
        _showAds.update { false }
    }

    fun continueApp() {
        _continueApp.update { true }
    }
}