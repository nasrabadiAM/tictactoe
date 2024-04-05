package me.nasrabadiam.tictactoe

sealed class GameEvent {
    data class CellClicked(val index: Int) : GameEvent()
    data object RestartClicked : GameEvent()
    data object ReplayClicked : GameEvent()
}