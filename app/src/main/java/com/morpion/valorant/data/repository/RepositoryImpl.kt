package com.morpion.valorant.data.repository

import com.morpion.valorant.data.remote.ApiService
import com.morpion.valorant.data.remote.request.LanguageRequest
import com.morpion.valorant.domain.repository.Repository
import com.morpion.valorant.util.RequestSafe
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val api: ApiService) : Repository, RequestSafe {

    override suspend fun getAgent(languageRequest: LanguageRequest) = safeApiCall { api.getAgents(languageRequest.language) }

    override suspend fun getMaps() = safeApiCall { api.getMaps() }

    override suspend fun getWeapons(languageRequest: LanguageRequest) = safeApiCall { api.getWeapons(languageRequest.language) }

}