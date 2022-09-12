package com.colosoft.webservices.local.repository

import com.colosoft.webservices.WebServices
import com.colosoft.webservices.local.GameDao
import com.colosoft.webservices.local.LocalGame

class LocalGamesRepository {

    suspend fun saveGame(localGame: LocalGame) {
        val gameDao : GameDao = WebServices.database.GameDao()
        gameDao.createGame(localGame)
    }

    suspend fun getGames() = WebServices.database.GameDao().getGames()

    suspend fun deleteGame(localGame: LocalGame) {
        val gameDao : GameDao = WebServices.database.GameDao()
        gameDao.deleteGame(localGame)
    }

    suspend fun searchGame(id: Int?) = WebServices.database.GameDao().searchGame(id)
}