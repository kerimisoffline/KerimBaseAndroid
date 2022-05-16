package com.kuarkdijital.kerimbaseproject.data

import com.kuarkdijital.kerimbaseproject.data.models.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface BaseApi {

    @GET("/search/{query}")
    suspend fun getSearchResponse(
        @Path("query") query:String
    ): Response<SearchResponse>
}