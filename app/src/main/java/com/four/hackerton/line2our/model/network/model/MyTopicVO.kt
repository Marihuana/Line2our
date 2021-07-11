package com.four.hackerton.line2our.model.network.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MyTopicVO(
    @SerializedName("id") var id : Int,
    @SerializedName("station_id") var stationId : Int,
    @SerializedName("category_id") var categoryId : Int,
    @SerializedName("icon") var icon : String,
    @SerializedName("title") var title : String,
    @SerializedName("createdAt") var createdAt : String,
    @SerializedName("updatedAt") var updatedAt : String
) : Serializable
