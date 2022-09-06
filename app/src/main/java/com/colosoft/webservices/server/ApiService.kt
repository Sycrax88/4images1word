package com.colosoft.webservices.server

import com.colosoft.webservices.server.model.FreeGamesList
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("https://www.freetogame.com/api/games\n")
    fun getGames(): FreeGamesList

}