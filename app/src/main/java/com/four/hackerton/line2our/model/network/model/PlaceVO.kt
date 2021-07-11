package com.four.hackerton.line2our.model.network.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PlaceVO(
    @SerializedName("id") var id : Int,
    @SerializedName("topic_id") var topicId : Int,
    @SerializedName("place_url") var url : String,
    @SerializedName("place_name") var name : String,
    @SerializedName("address_name") var address : String,
    @SerializedName("phone") var phone : String,
    @SerializedName("longitude") var longitude : Double,
    @SerializedName("latitude") var latitude : Double,
    @SerializedName("distance") var distance : String?,
    @SerializedName("likes") var likes : Int = 0,
    @SerializedName("bookmarks") var bookmarks : Int = 0,
    @SerializedName("like_use") var isLike : Boolean = false,
    @SerializedName("bookmark_use") var isFavorite : Boolean = false
) : Serializable {
    fun getLikes() : String {
        return likes.toString()
    }
    fun getBookmarks() : String {
        return bookmarks.toString()
    }
}

data class TopicItemData(
    @SerializedName("TopicItemData") var list : List<PlaceVO>,
    @SerializedName("userCount") var userCount : Int
)
