package com.colosoft.webservices.server.freegamesrepository

import com.colosoft.webservices.server.FreeGameDB

class FreeGamesRepository {

    suspend fun getGames() = FreeGameDB.retrofit.getGames()

}