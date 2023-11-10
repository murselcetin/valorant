package com.morpion.valorant.data.remote.response

import com.google.gson.annotations.SerializedName

data class MediaData(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("wave")
    val wave: String?,
    @SerializedName("wwise")
    val wwise: String?
)
