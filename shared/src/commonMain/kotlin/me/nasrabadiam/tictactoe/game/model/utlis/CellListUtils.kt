package me.nasrabadiam.tictactoe.game.model.utlis

import androidx.lifecycle.SavedStateHandle
import me.nasrabadiam.tictactoe.game.model.Cell
import me.nasrabadiam.tictactoe.util.Decoder
import me.nasrabadiam.tictactoe.util.Encoder
import kotlinx.serialization.serializer as genericSerializer

internal fun List<Cell>.saveState(savedStateHandle: SavedStateHandle) {
    savedStateHandle[CELLS_KEY] = this.encode()
}

internal fun getCellsListState(savedStateHandle: SavedStateHandle): List<Cell> {
    return savedStateHandle.get<String?>(CELLS_KEY).decodeCellList() ?: listOfEmptyCells()
}

private fun String?.decodeCellList(): List<Cell>? {
    return Decoder.decodeString(genericSerializer<List<Cell>>(), this)
}

private fun List<Cell>.encode(): String {
    return Encoder.encodeToString(genericSerializer<List<Cell>>(), this)
}

internal const val CELLS_KEY = "cells_key"