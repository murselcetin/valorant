package com.morpion.valorant.data.repository

import com.morpion.valorant.data.remote.ApiService
import com.morpion.valorant.domain.repository.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val api: ApiService) : Repository {

}