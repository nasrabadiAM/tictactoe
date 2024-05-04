package me.nasrabadiam.tictactoe.game.ui

sealed class GameEvent {
    data class CellClicked(val index: Int) : GameEvent()
    data object RestartClicked : GameEvent()
    data object ReplayClicked : GameEvent()
    data object RulesClicked : GameEvent()
}