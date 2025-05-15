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
import me.nasrabadiam.tictactoe.game.ui.GameEvent.CellClicked
import me.nasrabadiam.tictactoe.game.ui.GameEvent.ReplayClicked
import me.nasrabadiam.tictactoe.game.ui.GameEvent.RestartClicked
import me.nasrabadiam.tictactoe.game.ui.GameEvent.RulesClicked
import me.nasrabadiam.tictactoe.game.model.utlis.saveState
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject

@Inject
class GameViewModel(
    private val gameUseCase: GameUseCase,
    @Assisted val savedStateHandle: SavedStateHandle,
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
                cells.saveState(savedStateHandle)
                _state.update { state.value.copy(cells = cells) }
            }.launchIn(viewModelScope)
            gameResult.onEach { result ->
                result?.saveState(savedStateHandle)
                _state.update { state.value.copy(gameResult = result) }
            }.launchIn(viewModelScope)
            xScore.onEach { xScore ->
                xScore.saveXScore(savedStateHandle)
                _state.update {
                    state.value.copy(
                        scores = state.value.scores.copy(xScore = xScore)
                    )
                }
            }.launchIn(viewModelScope)
            oScore.onEach { oScore ->
                oScore.saveOScore(savedStateHandle)
                _state.update {
                    state.value.copy(
                        scores = state.value.scores.copy(oScore = oScore)
                    )
                }
            }.launchIn(viewModelScope)
            drawCount.onEach { drawCount ->
                drawCount.saveDrawCount(savedStateHandle)
                _state.update {
                    state.value.copy(
                        scores = state.value.scores.copy(drawCount = drawCount)
                    )
                }
            }.launchIn(viewModelScope)
            currentPlayer.onEach { player ->
                player.saveState(savedStateHandle)
                _state.update {
                    state.value.copy(
                        currentPlayer = player
                    )
                }
            }.launchIn(viewModelScope)
        }
    }

    private fun fetchGameState(): GameState {
        return GameState.getState(savedStateHandle)
    }
}