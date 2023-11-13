package com.morpion.valorant.presentation.agents

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.morpion.valorant.data.remote.request.GetAgentsRequest
import com.morpion.valorant.domain.model.AgentModel
import com.morpion.valorant.domain.usecase.GetAgentsUseCase
import com.morpion.valorant.util.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AgentsViewModel @Inject constructor(private val getAgentsUseCase: GetAgentsUseCase) :
    BaseViewModel() {

    private var allAgents = listOf<AgentModel>()
    var allRole = ArrayList<String>()
    private val _state = mutableStateOf(AgentsState())
    val state: State<AgentsState> = _state

    private var _searchRole = mutableStateOf("")
    val searchRole: State<String> = _searchRole

    init {
        getAgents()
    }

    private fun getRole(agents : List<AgentModel>){
        agents.forEach { itAgent ->
            if (!allRole.contains(itAgent.role?.displayName)) {
                allRole.add(itAgent.role?.displayName?:"")
            }
        }
    }

    private fun getAgents() {
        viewModelScope.launch {
            request(
                flow = getAgentsUseCase(input = GetAgentsRequest("tr-TR")),
                onLoading = { _state.value = AgentsState(isLoading = true) },
                onSuccess = { itAgents ->
                    itAgents.let {
                        _state.value = AgentsState(agents = it)
                        allAgents = it
                        getRole(allAgents)
                    }
                },
                onError = {
                    _state.value = AgentsState(error = it.toString())
                }
            )
        }
    }

    fun filterRole(role: String) {
        _searchRole.value = role
        _state.value = AgentsState(isLoading = true)

        val foundAgents =
            allAgents.filter {
                (it.role?.displayName?:"").contains(role, true)
            }

        if (foundAgents.isEmpty()) {
            _state.value = AgentsState(error = "No agents matching with your search")
        }   else {
            _state.value = AgentsState(agents = foundAgents)
        }
    }

}