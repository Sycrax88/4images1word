package com.colosoft.webservices.server.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Level(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("level_name")
    val levelName: String?,
    @SerializedName("image1_url")
    val image1url: String?,
    @SerializedName("image2_url")
    val image2url: String?,
    @SerializedName("image3_url")
    val image3url: String?,
    @SerializedName("image4_url")
    val image4url: String?,
    @SerializedName("answer")
    val answer: String?,
    @SerializedName("hint")
    val hint: String?,
    @SerializedName("description_image1")
    val descriptionImage1: String?,
    @SerializedName("description_image2")
    val descriptionImage2: String?,
    @SerializedName("description_image3")
    val descriptionImage3: String?,
    @SerializedName("description_image4")
    val descriptionImage4: String?
):Serializable