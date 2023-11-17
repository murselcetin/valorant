package com.morpion.valorant.presentation.maps

import com.morpion.valorant.domain.model.MapModel

data class MapsState(
    val isLoading: Boolean = false,
    val maps: List<MapModel> = emptyList(),
    val error: String = ""
)