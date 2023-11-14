package com.morpion.valorant.presentation.agents

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.airbnb.lottie.utils.MiscUtils.contains
import com.morpion.valorant.data.remote.request.LanguageRequest
import com.morpion.valorant.data.remote.response.RoleData
import com.morpion.valorant.domain.model.AgentModel
import com.morpion.valorant.domain.usecase.GetAgentsUseCase
import com.morpion.valorant.util.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

@HiltViewModel
class AgentsViewModel @Inject constructor(private val getAgentsUseCase: GetAgentsUseCase) :
    BaseViewModel() {

    private var allAgents = listOf<AgentModel>()
    var allRole = ArrayList<RoleData>()
    private val _state = mutableStateOf(AgentsState())
    val state: State<AgentsState> = _state

    private var _searchRole = mutableStateOf("")
    val searchRole: State<String> = _searchRole

    private val lang = if(Locale.getDefault().displayLanguage == "Türkçe") {
        "tr-TR"
    } else {
        "en-US"
    }

    init {
        getAgents()
    }

    private fun getRole(agents : List<AgentModel>){
        agents.forEach { itAgent ->
            if (allRole.filter { s -> s == itAgent.role }.isEmpty()) {
                allRole.add(itAgent.role?:RoleData())
            }
        }
    }

    private fun getAgents() {
        viewModelScope.launch {
            request(
                flow = getAgentsUseCase(input = LanguageRequest(lang)),
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

        val foundAgents = allAgents.filter { itAgent ->
                (itAgent.role?.displayName?:"").contains(role, true)
        }

        if (foundAgents.isEmpty()) {
            _state.value = AgentsState(error = "No agents")
        }   else {
            _state.value = AgentsState(agents = foundAgents)
        }
    }

}