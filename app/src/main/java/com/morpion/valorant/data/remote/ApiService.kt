package com.morpion.valorant.data.remote

import com.morpion.valorant.data.remote.response.GetAgentsResponse
import com.morpion.valorant.data.remote.response.GetMapsResponse
import com.morpion.valorant.util.BaseResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("v1/agents/?isPlayableCharacter=true")
    suspend fun getAgents(@Query("language") language: String?): Response<BaseResponse<List<GetAgentsResponse>>>

    @GET("v1/maps")
    suspend fun getMaps(): Response<BaseResponse<List<GetMapsResponse>>>

}