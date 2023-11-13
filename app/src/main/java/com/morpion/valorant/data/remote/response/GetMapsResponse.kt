package com.morpion.valorant.data.remote.response

import com.google.gson.annotations.SerializedName
import com.morpion.valorant.domain.model.AgentModel
import com.morpion.valorant.domain.model.MapModel

data class GetMapsResponse(
    @SerializedName("uuid") var uuid: String?,
    @SerializedName("displayName") var displayName: String?,
    @SerializedName("narrativeDescription") var narrativeDescription: String?,
    @SerializedName("tacticalDescription") var tacticalDescription: String,
    @SerializedName("coordinates") var coordinates: String?,
    @SerializedName("displayIcon") var displayIcon: String?,
    @SerializedName("listViewIcon") var listViewIcon: String?,
    @SerializedName("splash") var splash: String?,
    @SerializedName("assetPath") var assetPath: String?,
    @SerializedName("mapUrl") var mapUrl: String?,
    @SerializedName("xMultiplier") var xMultiplier: Double?,
    @SerializedName("yMultiplier") var yMultiplier: Double?,
    @SerializedName("xScalarToAdd") var xScalarToAdd: Double?,
    @SerializedName("yScalarToAdd") var yScalarToAdd: Double?,
    @SerializedName("callouts") var callouts: ArrayList<CalloutsData> = arrayListOf()
)

fun List<GetMapsResponse>.toMapModelList() = map {
    MapModel(
        uuid = it.uuid.orEmpty(),
        displayName = it.displayName.orEmpty(),
        coordinates = it.coordinates.orEmpty(),
        displayIcon = it.displayIcon.orEmpty(),
        splash = it.splash.orEmpty()
    )
}

