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
    @ColumnInfo(name = "answer") val answer: String?,
    @ColumnInfo(name = "hint") val hint: String?,
    @ColumnInfo(name = "description_image1") val descriptionImage1: String?,
    @ColumnInfo(name = "description_image2") val descriptionImage2: String?,
    @ColumnInfo(name = "description_image3") val descriptionImage3: String?,
    @ColumnInfo(name = "description_image4") val descriptionImage4: String?

    )