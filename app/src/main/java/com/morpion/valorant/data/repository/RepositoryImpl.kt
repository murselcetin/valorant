package com.morpion.valorant.data.repository

import com.morpion.valorant.data.remote.ApiService
import com.morpion.valorant.data.remote.request.GetAgentsRequest
import com.morpion.valorant.domain.repository.Repository
import com.morpion.valorant.util.RequestSafe
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val api: ApiService) : Repository, RequestSafe {

    override suspend fun getAgent(getAgentsRequest: GetAgentsRequest) = safeApiCall { api.getAgents(getAgentsRequest.language) }

    override suspend fun getMaps() = safeApiCall { api.getMaps() }

}