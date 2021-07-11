package com.four.hackerton.line2our.model.network.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class StationDetail(
    @SerializedName("detailData") var station : StationVO,
    @SerializedName("badge") var badge : Boolean
)

data class StationVO(
    @SerializedName("id") var id : Int,
    @SerializedName("name") var name : String,
    @SerializedName("prev_station_name") var prev : String,
    @SerializedName("prev_station_id") var prevId : Int,
    @SerializedName("next_station_name") var next : String,
    @SerializedName("next_station_id") var nextId : Int,
    @SerializedName("latitude") var latitude : Double,
    @SerializedName("longitude") var longitude : Double
) : Serializable
