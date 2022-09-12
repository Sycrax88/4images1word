package com.colosoft.webservices.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface GameDao {

    @Insert
    suspend fun createGame(game: LocalGame)

    @Query("SELECT * FROM table_game")
    suspend fun getGames() : MutableList<LocalGame>

    @Delete
    suspend fun deleteGame(localGame: LocalGame)

    @Query("SELECT * FROM table_game WHERE id LIKE :id")
    suspend fun searchGame(id: Int?): LocalGame
}