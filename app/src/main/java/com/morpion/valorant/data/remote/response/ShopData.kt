package com.morpion.valorant.data.remote.response

import com.google.gson.annotations.SerializedName

data class ShopData(
    @SerializedName("cost") var cost: Int?,
    @SerializedName("category") var category: String?,
    @SerializedName("categoryText") var categoryText: String?,
    @SerializedName("gridPosition") var gridPosition: GridPositionData?,
    @SerializedName("canBeTrashed") var canBeTrashed: Boolean?,
    @SerializedName("image") var image: String?,
    @SerializedName("newImage") var newImage: String?,
    @SerializedName("newImage2") var newImage2: String?,
    @SerializedName("assetPath") var assetPath: String?
)

data class GridPositionData(
    @SerializedName("row") var row: Int?,
    @SerializedName("column") var column: Int?
)