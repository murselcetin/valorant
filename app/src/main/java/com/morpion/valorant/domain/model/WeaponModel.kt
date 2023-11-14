package com.morpion.valorant.domain.model

import com.morpion.valorant.data.remote.response.SkinsData
import com.morpion.valorant.data.remote.response.WeaponsStatsData

data class WeaponModel(
    val uuid: String?,
    val displayName: String?,
    val category: String?,
    val displayIcon: String?,
    var skins: List<SkinsData>?,
    val weaponStats: WeaponsStatsData?
)
