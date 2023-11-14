package com.morpion.valorant.data.remote.response

import com.google.gson.annotations.SerializedName

data class SkinsData(
    @SerializedName("uuid") var uuid: String?,
    @SerializedName("displayName") var displayName: String?,
    @SerializedName("themeUuid") var themeUuid: String?,
    @SerializedName("contentTierUuid") var contentTierUuid: String?,
    @SerializedName("displayIcon") var displayIcon: String?,
    @SerializedName("wallpaper") var wallpaper: String?,
    @SerializedName("assetPath") var assetPath: String?,
    @SerializedName("chromas") var chromas: ArrayList<ChromasData> = arrayListOf(),
    @SerializedName("levels") var levels: ArrayList<LevelsData> = arrayListOf()
)

data class ChromasData(
    @SerializedName("uuid") var uuid: String?,
    @SerializedName("displayName") var displayName: String?,
    @SerializedName("displayIcon") var displayIcon: String?,
    @SerializedName("fullRender") var fullRender: String?,
    @SerializedName("swatch") var swatch: String?,
    @SerializedName("streamedVideo") var streamedVideo: String?,
    @SerializedName("assetPath") var assetPath: String?
)

data class LevelsData(
    @SerializedName("uuid") var uuid: String?,
    @SerializedName("displayName") var displayName: String?,
    @SerializedName("levelItem") var levelItem: String?,
    @SerializedName("displayIcon") var displayIcon: String?,
    @SerializedName("streamedVideo") var streamedVideo: String?,
    @SerializedName("assetPath") var assetPath: String?
)
