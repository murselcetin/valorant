package com.morpion.valorant.domain.usecase

import com.morpion.valorant.data.remote.request.LanguageRequest
import com.morpion.valorant.data.remote.response.toWeaponModelList
import com.morpion.valorant.domain.model.WeaponModel
import com.morpion.valorant.domain.repository.Repository
import com.morpion.valorant.util.RestResult
import com.morpion.valorant.util.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetWeaponsUseCase @Inject constructor(private val repository: Repository) :
    UseCaseWithFlow<LanguageRequest, RestResult<List<WeaponModel>>> {

    override suspend fun invoke(input: LanguageRequest): Flow<RestResult<List<WeaponModel>>> =
        flow {
            emit(
                repository.getWeapons(input).map {
                    it.toWeaponModelList()
                }
            )
        }

}