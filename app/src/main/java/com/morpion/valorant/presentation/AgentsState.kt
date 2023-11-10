package com.morpion.valorant.presentation

import com.morpion.valorant.domain.model.AgentModel

data class AgentsState(
    val isLoading: Boolean = false,
    val agents: List<AgentModel> = emptyList(),
    val error: String = ""
)