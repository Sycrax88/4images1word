package com.colosoft.webservices.server

import com.colosoft.webservices.server.model.LevelsList
import retrofit2.http.GET

interface ApiService {

    @GET("levels")
    suspend fun getLevels(): LevelsList

}