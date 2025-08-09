package me.nasrabadiam.tictactoe.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import me.nasrabadiam.tictactoe.game.model.AIDifficulty
import org.jetbrains.compose.resources.stringResource
import tictactoe.shared.generated.resources.Res
import tictactoe.shared.generated.resources.difficulty_easy
import tictactoe.shared.generated.resources.difficulty_hard
import tictactoe.shared.generated.resources.difficulty_normal

@Composable
fun DifficultyDialogScreen(
    navController: NavHostController,
    onDifficultySelected: (AIDifficulty) -> Unit
) {
    Dialog(
        onDismissRequest = { navController.popBackStack() },
    ) {
        Column(
            Modifier
                .background(
                    MaterialTheme.colorScheme.surface,
                    MaterialTheme.shapes.medium,
                )
                .padding(vertical = 40.dp, horizontal = 24.dp),
        ) {
            Button(
                modifier = Modifier
                    .widthIn(220.dp),
                colors = ButtonDefaults.buttonColors().copy(
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                onClick = {
                    onDifficultySelected.invoke(AIDifficulty.EASY)
                }
            ) {
                Text(
                    modifier = Modifier.padding(start = 8.dp),
                    text = stringResource(Res.string.difficulty_easy)
                )
            }
            Button(
                modifier = Modifier
                    .widthIn(220.dp),
                colors = ButtonDefaults.buttonColors().copy(
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                onClick = {
                    onDifficultySelected.invoke(AIDifficulty.NORMAL)
                }
            ) {
                Text(
                    modifier = Modifier.padding(start = 8.dp),
                    text = stringResource(Res.string.difficulty_normal)
                )
            }
            Button(
                modifier = Modifier
                    .widthIn(220.dp),
                colors = ButtonDefaults.buttonColors().copy(
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                onClick = {
                    onDifficultySelected.invoke(AIDifficulty.HARD)
                }
            ) {
                Text(
                    modifier = Modifier.padding(start = 8.dp),
                    text = stringResource(Res.string.difficulty_hard)
                )
            }
        }
    }
}
