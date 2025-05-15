package me.nasrabadiam.tictactoe.di

import me.nasrabadiam.tictactoe.di.scopes.AppScope
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides
import platform.UIKit.UIViewController

@AppScope
@Component
abstract class ApplicationComponent(
    @get:Provides val uiViewControllerProvider: () -> UIViewController,
) {

    companion object
}