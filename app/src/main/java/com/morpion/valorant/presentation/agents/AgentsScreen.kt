package com.morpion.valorant.presentation.agents

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.morpion.valorant.R
import com.morpion.valorant.presentation.components.ExitAlertDialog
import com.morpion.valorant.presentation.components.FilterChips
import com.morpion.valorant.domain.model.AgentModel
import com.morpion.valorant.presentation.MainActivity
import com.morpion.valorant.presentation.components.AgentsItem
import com.morpion.valorant.presentation.theme.White
import kotlin.system.exitProcess

@Composable
fun AgentsScreen(
    viewModel: AgentsViewModel = hiltViewModel(),
) {
    val openAlertDialog = remember { mutableStateOf(false) }
    val openAgentDetailBottomSheet = remember { mutableStateOf(false) }
    val selectedAgent = remember { mutableStateOf(AgentModel()) }
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
                    selectedItem = { itSelectedItem ->
                        viewModel.filterRole(itSelectedItem)
                    }
                )
            }

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(12.dp)
            ) {
                items(state.agents) { agentItem ->
                    AgentsItem(
                        agent = agentItem,
                        onItemClick = {
                            selectedAgent.value = it
                            openAgentDetailBottomSheet.value = true
                        }
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
                    dialogText = stringResource(R.string.exit_app),
                )
            }
        }
        if (openAgentDetailBottomSheet.value) {
            AgentDetailScreen(onDismissRequest = { openAgentDetailBottomSheet.value = false }, agent = selectedAgent.value)
        }
    }
}

