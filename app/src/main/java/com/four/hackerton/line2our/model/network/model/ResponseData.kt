package com.four.hackerton.line2our.model.network.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ResponseData<T> (
    @SerializedName("result") var result : String,
    @SerializedName("message") var message : String,
    @SerializedName("data") var data : T?
) : Serializable


