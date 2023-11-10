package com.morpion.valorant.presentation

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.AlertDialog
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.morpion.valorant.R
import com.morpion.valorant.common.components.FilterChips
import com.morpion.valorant.presentation.theme.White
import kotlin.system.exitProcess

@Composable
fun AgentsScreen(
    viewModel: AgentsViewModel = hiltViewModel(),
) {
    val openAlertDialog = remember { mutableStateOf(false) }
    val activity: MainActivity = MainActivity()

    val state = viewModel.state.value

    Box {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 12.dp)
        ) {

            if (viewModel.allRole.isNotEmpty()){
                FilterChips(
                    roleList = viewModel.allRole,
                    selectedRole = { itSelectedRole ->
                        viewModel.filterRole(itSelectedRole)
                    }
                )
            }

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(12.dp)
            ) {
                items(state.agents) { agentItem ->
                    AgentsItem(
                        agent = agentItem
                    )
                }
            }
        }

        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                color = White
            )
    }

        if (state.error.isNotBlank()) {
            Log.e("TAG", "AgentsScreen: ${state.error} ",)
        }

        BackHandler {
            openAlertDialog.value = true
        }

        when {
            openAlertDialog.value -> {
                ExitAlertDialog(
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
}

@Composable
fun ExitAlertDialog(
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