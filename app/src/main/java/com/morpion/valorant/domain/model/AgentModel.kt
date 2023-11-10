package com.morpion.valorant.domain.model

import com.morpion.valorant.data.remote.response.AbilitiesData
import com.morpion.valorant.data.remote.response.RoleData

data class AgentModel(
    val uuid: String,
    val displayName: String,
    val description: String,
    val displayIcon: String,
    val fullPortrait: String,
    val background: String,
    val backgroundGradientColors: List<String>,
    val role: RoleData?,
    val abilities: List<AbilitiesData>,
)
