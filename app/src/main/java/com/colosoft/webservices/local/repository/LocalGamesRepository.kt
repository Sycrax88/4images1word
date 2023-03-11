package com.colosoft.webservices.local.repository

import com.colosoft.webservices.WebServices
import com.colosoft.webservices.local.GameDao
import com.colosoft.webservices.local.LocalGame

class LocalGamesRepository {

    suspend fun saveGame(localGame: LocalGame) {
        val gameDao : GameDao = WebServices.database.GameDao()
        gameDao.createLevel(localGame)
    }

    suspend fun getLevels() = WebServices.database.GameDao().getLevels()

    suspend fun deleteLevel(localGame: LocalGame) {
        val gameDao : GameDao = WebServices.database.GameDao()
        gameDao.deleteLevel(localGame)
    }

    suspend fun searchLevel(id: Int?) = WebServices.database.GameDao().searchLevel(id)
}