package com.morpion.valorant.domain.usecase

import com.morpion.valorant.data.remote.response.toMapModelList
import com.morpion.valorant.domain.model.MapModel
import com.morpion.valorant.domain.repository.Repository
import com.morpion.valorant.util.RestResult
import com.morpion.valorant.util.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMapsUseCase @Inject constructor(private val repository: Repository) :
    NoInputUseCaseWithFlow<RestResult<List<MapModel>>> {

    override suspend fun invoke(): Flow<RestResult<List<MapModel>>> =
        flow {
            emit(
                repository.getMaps().map {
                    it.toMapModelList()
                }
            )
        }

}