package com.four.hackerton.line2our.model.network.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Authorization (
    @SerializedName("token") var token : TokenVO,
    @SerializedName("user") var user : UserVO,
) : Serializable

data class TokenVO (
    @SerializedName("accesToken") var accesToken : String,
    @SerializedName("refreshToken") var refreshToken : String,
) : Serializable

data class UserVO (
    @SerializedName("email") var email : String,
    @SerializedName("name") var name : String,
) : Serializable
