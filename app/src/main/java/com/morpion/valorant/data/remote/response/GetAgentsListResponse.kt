package com.morpion.valorant.data.remote.response

import com.google.gson.annotations.SerializedName
import com.morpion.valorant.domain.model.AgentModel

data class GetAgentsListResponse(
    val data: List<GetAgentsResponse>?
)

data class GetAgentsResponse(
    @SerializedName("uuid") var uuid: String? = null,
    @SerializedName("displayName") var displayName: String? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("developerName") var developerName: String? = null,
    @SerializedName("characterTags") var characterTags: Any? = null,
    @SerializedName("displayIcon") var displayIcon: String? = null,
    @SerializedName("displayIconSmall") var displayIconSmall: String? = null,
    @SerializedName("bustPortrait") var bustPortrait: String? = null,
    @SerializedName("fullPortrait") var fullPortrait: String? = null,
    @SerializedName("fullPortraitV2") var fullPortraitV2: String? = null,
    @SerializedName("killfeedPortrait") var killFeedPortrait: String? = null,
    @SerializedName("background") var background: String? = null,
    @SerializedName("backgroundGradientColors") var backgroundGradientColors: ArrayList<String> = arrayListOf(),
    @SerializedName("assetPath") var assetPath: String? = null,
    @SerializedName("isFullPortraitRightFacing") var isFullPortraitRightFacing: Boolean? = null,
    @SerializedName("isPlayableCharacter") var isPlayableCharacter: Boolean? = null,
    @SerializedName("isAvailableForTest") var isAvailableForTest: Boolean? = null,
    @SerializedName("isBaseContent") var isBaseContent: Boolean? = null,
    @SerializedName("role") var role: RoleData? = RoleData(),
    @SerializedName("recruitmentData") var recruitmentData: Any? = null,
    @SerializedName("abilities") var abilities: ArrayList<AbilitiesData> = arrayListOf(),
    @SerializedName("voiceLine") var voiceLine: Any? = null
)

fun List<GetAgentsResponse>.toAgentModelList() = map {
    AgentModel(
        uuid = it.uuid.orEmpty(),
        displayName = it.displayName.orEmpty(),
        description = it.description.orEmpty(),
        displayIcon = it.displayIcon.orEmpty(),
        fullPortrait = it.fullPortrait.orEmpty(),
        background = it.background.orEmpty(),
        backgroundGradientColors = it.backgroundGradientColors,
        role = it.role,
        abilities = it.abilities,
    )
}