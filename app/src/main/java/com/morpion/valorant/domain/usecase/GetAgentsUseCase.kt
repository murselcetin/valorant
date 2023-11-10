package com.morpion.valorant.domain.usecase

import android.util.Log
import com.morpion.valorant.data.remote.request.GetAgentsRequest
import com.morpion.valorant.data.remote.response.toAgentModelList
import com.morpion.valorant.domain.model.AgentModel
import com.morpion.valorant.domain.repository.Repository
import com.morpion.valorant.util.RestResult
import com.morpion.valorant.util.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAgentsUseCase @Inject constructor(private val repository: Repository) :
    UseCaseWithFlow<GetAgentsRequest,RestResult<List<AgentModel>>> {

    override suspend fun invoke(input: GetAgentsRequest): Flow<RestResult<List<AgentModel>>> =
        flow {
            emit(
                repository.getAgent(input).map {
                    it.toAgentModelList()
                }
            )
        }

}