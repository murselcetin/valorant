package com.morpion.valorant.data.remote.response

import com.google.gson.annotations.SerializedName
import com.morpion.valorant.domain.model.WeaponModel

data class GetWeaponsResponse(
    @SerializedName("uuid") var uuid: String?,
    @SerializedName("displayName") var displayName: String?,
    @SerializedName("category") var category: String?,
    @SerializedName("defaultSkinUuid") var defaultSkinUuid: String?,
    @SerializedName("displayIcon") var displayIcon: String?,
    @SerializedName("killStreamIcon") var killStreamIcon: String?,
    @SerializedName("assetPath") var assetPath: String?,
    @SerializedName("weaponStats") var weaponStats: WeaponsStatsData?,
    @SerializedName("shopData") var shopData: ShopData?,
    @SerializedName("skins") var skins: ArrayList<SkinsData> = arrayListOf()
)


fun List<GetWeaponsResponse>.toWeaponModelList() = map {
    WeaponModel(
        uuid = it.uuid,
        displayName= it.displayName,
        category= it.category,
        displayIcon= it.displayIcon,
        skins = it.skins,
        weaponStats = it.weaponStats
    )
}
