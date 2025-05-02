package me.nasrabadiam.tictactoe.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher

@OptIn(ExperimentalCoroutinesApi::class)
fun TestScope.launchInTest(body: suspend CoroutineScope.() -> Unit): Job {
    return launch(UnconfinedTestDispatcher(testScheduler)) {
        body.invoke(this)
    }
}