package com.colosoft.webservices.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName= "table_game")
data class LocalGame (
    @PrimaryKey @ColumnInfo(name = "id") val id: Int?,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "releaseDate") val releaseDate: String?,
    @ColumnInfo(name = "publisher") val publisher: String?,
    @ColumnInfo(name = "developer") val developer: String?,
    @ColumnInfo(name = "platform") val platform: String?,
    @ColumnInfo(name = "genre") val genre: String?,
    @ColumnInfo(name = "summary") val summary: String?,

    )