package com.morpion.valorant.domain.model

import com.morpion.valorant.data.remote.response.SkinsData
import com.morpion.valorant.data.remote.response.WeaponsStatsData

data class WeaponModel(
    val uuid: String? = null,
    val displayName: String? = null,
    val category: String? = null,
    val displayIcon: String? = null,
    var skins: List<SkinsData>? = emptyList(),
    val weaponStats: WeaponsStatsData? = null
)
