package com.morpion.valorant.domain.repository

import com.morpion.valorant.data.remote.request.LanguageRequest
import com.morpion.valorant.data.remote.response.GetAgentsResponse
import com.morpion.valorant.data.remote.response.GetMapsResponse
import com.morpion.valorant.data.remote.response.GetWeaponsResponse
import com.morpion.valorant.util.RestResult

interface Repository {
    suspend fun getAgent(languageRequest: LanguageRequest): RestResult<List<GetAgentsResponse>>
    suspend fun getMaps(): RestResult<List<GetMapsResponse>>
    suspend fun getWeapons(languageRequest: LanguageRequest): RestResult<List<GetWeaponsResponse>>
}