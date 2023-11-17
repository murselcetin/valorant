package com.morpion.valorant.data.remote.request

import com.google.gson.annotations.SerializedName

data class LanguageRequest(
    @SerializedName("language")
    val language:String = "en-US"
)
