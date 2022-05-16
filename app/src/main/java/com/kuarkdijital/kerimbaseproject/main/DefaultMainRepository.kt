package com.kuarkdijital.kerimbaseproject.main

import com.kuarkdijital.kerimbaseproject.data.BaseApi
import com.kuarkdijital.kerimbaseproject.data.models.SearchResponse
import com.kuarkdijital.kerimbaseproject.util.Resource
import java.lang.Exception
import javax.inject.Inject

class DefaultMainRepository @Inject constructor(
    private val api : BaseApi
) : MainRepository {

    override suspend fun getRates(query: String): Resource<SearchResponse> {
        return try {
            val response = api.getSearchResponse(query)
            val result = response.body()
            if(response.isSuccessful && result != null){
                Resource.Success(result)
            } else {
                Resource.Error(response.message())
            }
        } catch (e: Exception){
            Resource.Error(e.message ?: "An error occured.")
        }
    }
}