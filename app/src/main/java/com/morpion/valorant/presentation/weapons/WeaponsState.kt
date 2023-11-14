package com.morpion.valorant.presentation.weapons

import com.morpion.valorant.domain.model.AgentModel
import com.morpion.valorant.domain.model.WeaponModel

data class WeaponsState(
    val isLoading: Boolean = false,
    val weapons: List<WeaponModel> = emptyList(),
    val error: String = ""
)