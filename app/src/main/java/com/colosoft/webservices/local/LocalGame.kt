package com.colosoft.webservices.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName= "table_game")
data class LocalGame (
    @PrimaryKey @ColumnInfo(name = "id") val id: Int?,
    @ColumnInfo(name = "level_name") val levelName: String?,
    @ColumnInfo(name = "image1_url") val image1url: String?,
    @ColumnInfo(name = "image2_url") val image2url: String?,
    @ColumnInfo(name = "image3_url") val image3url: String?,
    @ColumnInfo(name = "image4_url") val image4url: String?,
    @ColumnInfo(name = "answer") val answer: String?

    )