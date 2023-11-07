package com.morpion.valorant.presentation

import androidx.activity.compose.BackHandler
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import com.morpion.valorant.R
import kotlin.system.exitProcess

@Composable
fun AgentsScreen(
) {
    val openAlertDialog = remember { mutableStateOf(false) }
    val activity: MainActivity = MainActivity()

    Text("Agents Page")

    BackHandler {
        openAlertDialog.value = true
    }

    when {
        openAlertDialog.value -> {
            AlertDialogExample(
                onDismissRequest = { openAlertDialog.value = false },
                onConfirmation = {
                    openAlertDialog.value = false
                    activity.finish()
                    exitProcess(0)
                },
                dialogTitle = stringResource(R.string.app_name),
                dialogText = "Uygumaladan çıkmak istediğinize emin misiniz?",
            )
        }
    }
}

@Composable
fun AlertDialogExample(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
) {
    AlertDialog(
        title = {
            Text(text = dialogTitle)
        },
        text = {
            Text(text = dialogText)
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
                Text("Evet")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text("Hayır")
            }
        }
    )
}