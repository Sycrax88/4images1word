package com.colosoft.webservices.server.levelsrepository

import com.colosoft.webservices.server.LevelDB

class LevelsRepository {

    suspend fun getLevels() = LevelDB.retrofit.getLevels()

}