package com.morpion.valorant.presentation.components

import androidx.compose.foundation.background
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.morpion.valorant.R
import com.morpion.valorant.presentation.theme.*

@Composable
fun ExitAlertDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
) {
    AlertDialog(
        backgroundColor = LightBlack,
        title = {
            Text(text = dialogTitle, style = titleWhite.copy(color = LightRed))
        },
        text = {
            Text(text = dialogText, style = normalWhite)
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text(text = stringResource(id = R.string.yes), style = normalWhite)
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text(text = stringResource(id = R.string.no), style = normalRed)
            }
        }
    )
}