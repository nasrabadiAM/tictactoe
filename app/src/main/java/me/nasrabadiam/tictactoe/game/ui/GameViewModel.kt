package me.nasrabadiam.tictactoe.game.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import me.nasrabadiam.tictactoe.game.GameUseCase
import me.nasrabadiam.tictactoe.game.model.Player
import me.nasrabadiam.tictactoe.game.model.utlis.listOfEmptyCells
import me.nasrabadiam.tictactoe.game.ui.GameEvent.CellClicked
import me.nasrabadiam.tictactoe.game.ui.GameEvent.ReplayClicked
import me.nasrabadiam.tictactoe.game.ui.GameEvent.RestartClicked
import me.nasrabadiam.tictactoe.game.ui.GameEvent.RulesClicked
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject

@Inject
class GameViewModel(
    private val gameUseCase: GameUseCase,
    @Assisted val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = MutableStateFlow(fetchGameState())
    val state = _state.asStateFlow()

    init {
        gameUseCase.restoreGameState(fetchGameState())
        observeGameUseCase()
    }

    fun handleEvent(event: GameEvent) {
        when (event) {
            is CellClicked -> gameUseCase.clickOnCell(event.index)
            ReplayClicked -> gameUseCase.replayGame()
            RestartClicked -> gameUseCase.restartGame()
            RulesClicked -> rulesClicked()
        }
    }

    private fun rulesClicked() {
        // No-op -> "Not yet implemented", Probably navigate to rules screen
    }

    private fun observeGameUseCase() {

        with(gameUseCase) {
            cells.onEach { cells ->
                savedStateHandle[CELLS_KEY] = cells
                _state.update { state.value.copy(cells = cells) }
            }.launchIn(viewModelScope)
            gameResult.onEach { result ->
                savedStateHandle[GAME_RESULT_KEY] = result
                _state.update { state.value.copy(gameResult = result) }
            }.launchIn(viewModelScope)
            xScore.onEach { xScore ->
                savedStateHandle[X_SCORE_KEY] = xScore
                _state.update {
                    state.value.copy(
                        scores = state.value.scores.copy(xScore = xScore)
                    )
                }
            }.launchIn(viewModelScope)
            oScore.onEach { oScore ->
                savedStateHandle[O_SCORE_KEY] = oScore
                _state.update {
                    state.value.copy(
                        scores = state.value.scores.copy(oScore = oScore)
                    )
                }
            }.launchIn(viewModelScope)
            drawCount.onEach { drawCount ->
                savedStateHandle[DRAW_COUNT_SCORE_KEY] = drawCount
                _state.update {
                    state.value.copy(
                        scores = state.value.scores.copy(drawCount = drawCount)
                    )
                }
            }.launchIn(viewModelScope)
            currentPlayer.onEach { player ->
                savedStateHandle[CURRENT_PLAYER] = player
                _state.update {
                    state.value.copy(
                        currentPlayer = player
                    )
                }
            }.launchIn(viewModelScope)
        }
    }

    private fun fetchGameState(): GameState {
        return GameState(
            gameResult = savedStateHandle[GAME_RESULT_KEY],
            cells = savedStateHandle[CELLS_KEY] ?: listOfEmptyCells(),
            currentPlayer = savedStateHandle[CURRENT_PLAYER] ?: Player.X,
            scores = ScoresState(
                xScore = savedStateHandle[X_SCORE_KEY] ?: 0,
                oScore = savedStateHandle[O_SCORE_KEY] ?: 0,
                drawCount = savedStateHandle[DRAW_COUNT_SCORE_KEY] ?: 0
            )
        )
    }

    companion object {
        private const val CELLS_KEY = "cells"
        private const val GAME_RESULT_KEY = "game_result"
        private const val X_SCORE_KEY = "x_score"
        private const val O_SCORE_KEY = "o_score"
        private const val CURRENT_PLAYER = "current_player"
        private const val DRAW_COUNT_SCORE_KEY = "draw_count"
    }
}