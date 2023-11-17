package com.morpion.valorant.util

import com.google.gson.annotations.SerializedName

data class BaseResponse<out T>(
    @SerializedName("status") var status: Int,
    @SerializedName("data") val data: T
)
