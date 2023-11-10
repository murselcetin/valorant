package com.morpion.valorant.data.remote.request

import com.google.gson.annotations.SerializedName

data class GetAgentsRequest(
    @SerializedName("language")
    val language:String = "en-US"
)
