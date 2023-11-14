package com.morpion.valorant.data.remote.response

import com.google.gson.annotations.SerializedName

data class CalloutsData(
    @SerializedName("regionName") var regionName: String?,
    @SerializedName("superRegionName") var superRegionName: String?,
    @SerializedName("location") var location: LocationsData?
)

data class LocationsData(
    @SerializedName("x") var x: Double?,
    @SerializedName("y") var y: Double?
)
