package com.morpion.valorant.domain.model

import com.morpion.valorant.data.remote.response.AbilitiesData
import com.morpion.valorant.data.remote.response.RoleData

data class AgentModel(
    val uuid: String? = null,
    val displayName: String? = null,
    val description: String? = null,
    val displayIcon: String? = null,
    val fullPortrait: String? = null,
    val background: String? = null,
    val backgroundGradientColors: List<String>? = emptyList(),
    val role: RoleData? = null,
    val abilities: List<AbilitiesData>? = emptyList(),
)
