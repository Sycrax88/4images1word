package com.colosoft.webservices.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [LocalGame::class], version = 1)
abstract class GameDatabase : RoomDatabase() {

    abstract fun GameDao() : GameDao
}