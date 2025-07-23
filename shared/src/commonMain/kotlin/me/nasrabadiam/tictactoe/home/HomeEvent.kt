package me.nasrabadiam.tictactoe.home

sealed class HomeEvent {
    data object PlayWithAFriendEvent : HomeEvent()
    data object PlayWithAIEvent : HomeEvent()
}