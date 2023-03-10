package com.colosoft.webservices.server

import com.colosoft.webservices.server.model.FreeGamesList
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("levels")
    suspend fun getGames(): FreeGamesList

}