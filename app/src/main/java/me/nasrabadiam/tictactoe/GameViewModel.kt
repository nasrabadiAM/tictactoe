package me.nasrabadiam.tictactoe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import me.nasrabadiam.tictactoe.GameEvent.CellClicked
import me.nasrabadiam.tictactoe.GameEvent.ReplayClicked
import me.nasrabadiam.tictactoe.GameEvent.RestartClicked
import me.nasrabadiam.tictactoe.game.GameUseCase
import me.tatarka.inject.annotations.Inject

@Inject
class GameViewModel(
    private val gameUseCase: GameUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(GameState())
    val state = _state.asStateFlow()

    init {
        observeGameUseCase()
    }

    fun handleEvent(event: GameEvent) {
        when (event) {
            is CellClicked -> gameUseCase.clickOnCell(event.index)
            ReplayClicked -> gameUseCase.replayGame()
            RestartClicked -> gameUseCase.restartGame()
        }
    }

    private fun observeGameUseCase() {

        with(gameUseCase) {
            cells.onEach { cells ->
                _state.update { state.value.copy(cells = cells) }
            }.launchIn(viewModelScope)
            gameResult.onEach { result ->
                _state.update { state.value.copy(gameResult = result) }
            }.launchIn(viewModelScope)
            xScore.onEach { xScore ->
                _state.update {
                    state.value.copy(
                        scores = _state.value.scores.copy(xScore = xScore)
                    )
                }
            }.launchIn(viewModelScope)
            oScore.onEach { oScore ->
                _state.update {
                    state.value.copy(
                        scores = _state.value.scores.copy(oScore = oScore)
                    )
                }
            }.launchIn(viewModelScope)

            drawCount.onEach { drawCount ->
                _state.update {
                    state.value.copy(
                        scores = _state.value.scores.copy(drawCount = drawCount)
                    )
                }
            }.launchIn(viewModelScope)
        }
    }
}