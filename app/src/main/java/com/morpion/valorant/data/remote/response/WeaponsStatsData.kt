package com.morpion.valorant.data.remote.response

import com.google.gson.annotations.SerializedName

data class WeaponsStatsData(
    @SerializedName("fireRate") var fireRate: Double?,
    @SerializedName("magazineSize") var magazineSize: Int?,
    @SerializedName("runSpeedMultiplier") var runSpeedMultiplier: Double?,
    @SerializedName("equipTimeSeconds") var equipTimeSeconds: Double?,
    @SerializedName("reloadTimeSeconds") var reloadTimeSeconds: Double?,
    @SerializedName("firstBulletAccuracy") var firstBulletAccuracy: Double?,
    @SerializedName("shotgunPelletCount") var shotgunPelletCount: Int?,
    @SerializedName("wallPenetration") var wallPenetration: String?,
    @SerializedName("feature") var feature: String?,
    @SerializedName("fireMode") var fireMode: String?,
    @SerializedName("altFireType") var altFireType: String?,
    @SerializedName("adsStats") var adsStats: AdsStatsData?,
    @SerializedName("altShotgunStats") var altShotgunStats: Any?,
    @SerializedName("airBurstStats") var airBurstStats: Any?,
    @SerializedName("damageRanges") var damageRanges: ArrayList<DamageRangesData> = arrayListOf()
)

data class AdsStatsData(
    @SerializedName("zoomMultiplier") var zoomMultiplier: Double?,
    @SerializedName("fireRate") var fireRate: Double?,
    @SerializedName("runSpeedMultiplier") var runSpeedMultiplier: Double?,
    @SerializedName("burstCount") var burstCount: Int?,
    @SerializedName("firstBulletAccuracy") var firstBulletAccuracy: Double?
)

data class DamageRangesData(
    @SerializedName("rangeStartMeters") var rangeStartMeters: Int?,
    @SerializedName("rangeEndMeters") var rangeEndMeters: Int?,
    @SerializedName("headDamage") var headDamage: Double?,
    @SerializedName("bodyDamage") var bodyDamage: Int?,
    @SerializedName("legDamage") var legDamage: Double?
)