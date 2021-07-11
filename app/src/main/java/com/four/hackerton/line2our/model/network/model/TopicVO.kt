package com.four.hackerton.line2our.model.network.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class TopicVO(
    @SerializedName("id") var id : Int,
    @SerializedName("icon") var icon : String,
    @SerializedName("title") var title : String
) : Serializable
