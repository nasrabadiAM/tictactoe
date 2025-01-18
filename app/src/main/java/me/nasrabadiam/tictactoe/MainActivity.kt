package me.nasrabadiam.tictactoe

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration
import com.google.android.gms.ads.initialization.AdapterStatus
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import me.nasrabadiam.tictactoe.di.ApplicationComponent
import me.nasrabadiam.tictactoe.di.scopes.ActivityScope
import me.nasrabadiam.tictactoe.game.GameUseCase
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides
import java.util.concurrent.atomic.AtomicBoolean

class MainActivity : ComponentActivity() {

    private val adsViewModel by viewModels<AdsViewModel>()
    private var adIsLoading = false
    private var interstitialAd: InterstitialAd? = null
    private val isMobileAdsInitializeCalled = AtomicBoolean(false)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityComponent = ActivityComponent::class.create(activity = this)
        setContent { activityComponent.app() }
        initializeAdsSdk()
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                adsViewModel.showAdsState.collect { adsState ->
                    if (adsState) {
                        showInterstitial { adsViewModel.continueApp() }
                        adsViewModel.adShown()
                    }
                }
            }
        }
    }

    private fun initializeAdsSdk() {
        if (isMobileAdsInitializeCalled.getAndSet(true)) {
            return
        }
        // Set your test devices.
        MobileAds.setRequestConfiguration(
            RequestConfiguration.Builder().setTestDeviceIds(listOf(TEST_DEVICE_HASHED_ID))
                .build()
        )
        lifecycleScope.launch(Dispatchers.Default) {
            MobileAds.initialize(this@MainActivity) { initState ->
                val statusMap = initState.adapterStatusMap
                for (adapterClass in statusMap.keys) {
                    val status: AdapterStatus? = statusMap[adapterClass]
                    Log.d(
                        "MyApp",
                        String.format(
                            "Adapter name: %s, Description: %s, Latency: %d",
                            adapterClass, status?.description, status?.latency
                        )
                    )
                    runOnUiThread {
                        loadAd()
                    }
                }
            }
        }
    }

    private fun loadAd() {
        // Request a new ad if one isn't already loaded.
        if (adIsLoading || interstitialAd != null) {
            return
        }
        adIsLoading = true

        val adRequest = AdRequest.Builder().build()

        InterstitialAd.load(
            this,
            AD_UNIT_ID,
            adRequest,
            object : InterstitialAdLoadCallback() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    interstitialAd = null
                    adIsLoading = false
                    val error =
                        "domain: ${adError.domain}, code: ${adError.code}, " + "message: ${adError.message}"
                    Log.d(TAG, error + adError.message)

                    Toast.makeText(
                        this@MainActivity,
                        "onAdFailedToLoad() with error $error",
                        Toast.LENGTH_SHORT,
                    ).show()
                }

                override fun onAdLoaded(ad: InterstitialAd) {
                    Log.d(TAG, "Ad was loaded.")
                    interstitialAd = ad
                    adIsLoading = false
                    Toast.makeText(this@MainActivity, "onAdLoaded()", Toast.LENGTH_SHORT).show()
                }
            },
        )
    }

    private fun showInterstitial(ifAdNotReady: () -> Unit) {
        if (interstitialAd != null) {
            interstitialAd?.fullScreenContentCallback =
                object : FullScreenContentCallback() {
                    override fun onAdDismissedFullScreenContent() {
                        Log.d(TAG, "Ad was dismissed.")
                        // Don't forget to set the ad reference to null so you
                        // don't show the ad a second time.
                        interstitialAd = null
                    }

                    override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                        Log.d(TAG, "Ad failed to show.")
                        // Don't forget to set the ad reference to null so you
                        // don't show the ad a second time.
                        interstitialAd = null
                    }

                    override fun onAdShowedFullScreenContent() {
                        Log.d(TAG, "Ad showed fullscreen content.")
                        // Called when ad is dismissed.
                    }
                }
            interstitialAd?.show(this)
        } else {
            ifAdNotReady()
            loadAd()
        }
    }

    companion object {
        private const val TAG: String = "TicTacToe"
        private const val AD_UNIT_ID: String = "ca-app-pub-3940256099942544/1033173712"
        const val TEST_DEVICE_HASHED_ID = "ABCDEF012345"
    }
}

fun ApplicationComponent.Companion.from(activity: Activity): ApplicationComponent =
    (activity.applicationContext as TicTacToeApplication).appComponent

@Component
@ActivityScope
abstract class ActivityComponent(
    @get:Provides val activity: ComponentActivity,
    @Component val applicationComponent: ApplicationComponent = ApplicationComponent.from(activity)
) {

    abstract val gameUseCase: GameUseCase

    abstract val app: App
}