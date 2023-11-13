package com.morpion.valorant.presentation.maps

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.morpion.valorant.data.remote.request.GetAgentsRequest
import com.morpion.valorant.domain.model.AgentModel
import com.morpion.valorant.domain.usecase.GetAgentsUseCase
import com.morpion.valorant.domain.usecase.GetMapsUseCase
import com.morpion.valorant.presentation.agents.AgentsState
import com.morpion.valorant.util.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapsViewModel @Inject constructor(private val getMapsUseCase: GetMapsUseCase) : BaseViewModel()  {

    private val _state = mutableStateOf(MapsState())
    val state: State<MapsState> = _state

    init {
        getMaps()
    }

    private fun getMaps() {
        viewModelScope.launch {
            request(
                flow = getMapsUseCase(),
                onLoading = { _state.value = MapsState(isLoading = true) },
                onSuccess = { itAgents ->
                    itAgents.let {
                        _state.value = MapsState(maps = it)
                    }
                },
                onError = {
                    _state.value = MapsState(error = it.toString())
                }
            )
        }
    }
}