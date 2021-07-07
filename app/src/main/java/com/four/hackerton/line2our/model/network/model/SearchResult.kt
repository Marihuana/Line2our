package com.four.hackerton.line2our.model.network.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SearchResult (
    @SerializedName("documents") var documents : ArrayList<Document>
        ) : Serializable

data class Document(
    @SerializedName("contents") var contents : String,
    @SerializedName("datetime") var datetime : String,
    @SerializedName("title") var title : String,
    @SerializedName("url") var url : String,
)