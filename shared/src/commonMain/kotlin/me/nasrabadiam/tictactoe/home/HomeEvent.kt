package me.nasrabadiam.tictactoe.home

sealed class HomeEvent {
    data object PlayWithAFriend : HomeEvent()
    data object PlayWithAI : HomeEvent()
}