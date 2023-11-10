package com.morpion.valorant.data.remote.response

import com.google.gson.annotations.SerializedName

data class RoleData(
    @SerializedName("uuid") var uuid: String? = null,
    @SerializedName("displayName") var displayName: String? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("displayIcon") var displayIcon: String? = null,
    @SerializedName("assetPath") var assetPath: String? = null
)
