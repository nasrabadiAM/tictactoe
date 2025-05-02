package me.nasrabadiam.tictactoe.game.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import me.nasrabadiam.tictactoe.game.GameUseCase.Orientation

sealed class GameResult : Parcelable {
    @Parcelize
    data class EndWithWinner(
        val player: Player,
        val winningOrientation: Orientation,
        val winningIndex: Int,
    ) : GameResult()

    @Parcelize
    data object Draw : GameResult()
}