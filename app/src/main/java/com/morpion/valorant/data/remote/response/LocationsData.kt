package com.morpion.valorant.data.remote.response

import com.google.gson.annotations.SerializedName

data class LocationsData(
    @SerializedName("x") var x: Double?,
    @SerializedName("y") var y: Double?
)
