package com.colosoft.webservices.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface GameDao {

    @Insert
    suspend fun createLevel(game: LocalGame)

    @Query("SELECT * FROM table_game")
    suspend fun getLevels() : MutableList<LocalGame>

    @Delete
    suspend fun deleteLevel(localGame: LocalGame)

    @Query("SELECT * FROM table_game WHERE id LIKE :id")
    suspend fun searchLevel(id: Int?): LocalGame
}