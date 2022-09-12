package com.colosoft.webservices

import android.app.Application
import androidx.room.Room
import com.colosoft.webservices.local.GameDao
import com.colosoft.webservices.local.GameDatabase

class WebServices : Application() {

    companion object{
        lateinit var database: GameDatabase
    }

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(
            this,
            GameDatabase::class.java,
            "game_db"
        ).build()
    }
}