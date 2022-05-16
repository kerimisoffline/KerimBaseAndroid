package com.kuarkdijital.kerimbaseproject.main

import com.kuarkdijital.kerimbaseproject.data.models.SearchResponse
import com.kuarkdijital.kerimbaseproject.util.Resource
import java.util.*

interface MainRepository {

    suspend fun getRates(query:String) : Resource<SearchResponse>
}