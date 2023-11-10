package com.morpion.valorant.domain.repository

import com.morpion.valorant.data.remote.request.GetAgentsRequest
import com.morpion.valorant.data.remote.response.GetAgentsListResponse
import com.morpion.valorant.data.remote.response.GetAgentsResponse
import com.morpion.valorant.util.RestResult

interface Repository {
    suspend fun getAgent(getAgentsRequest:GetAgentsRequest): RestResult<List<GetAgentsResponse>>
}