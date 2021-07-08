package com.four.hackerton.line2our.model.network.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class KakaoResponse (
    @SerializedName("meta") var meta : Meta,
    @SerializedName("documents") var places : ArrayList<Place>
        ) : Serializable

data class Meta(
    @SerializedName("same_name") var sameName : SameName,
    @SerializedName("pageable_count") var pageableCount : Int,
    @SerializedName("total_count") var totalCount : Int,
    @SerializedName("is_end") var isEnd : Boolean
)

data class SameName(
    @SerializedName("region") var region : List<String>,
    @SerializedName("keyword") var keyword : String,
    @SerializedName("selected_region") var selectedRegion : String
)

data class Place(
    @SerializedName("id") var id : String,
    @SerializedName("place_name") var name : String,
    @SerializedName("phone") var phone : String,
    @SerializedName("distance") var distance : String,
    @SerializedName("place_url") var url : String,
    @SerializedName("category_name") var categoryName : String,
    @SerializedName("address_name") var addressName : String,
    @SerializedName("road_address_name") var roadAddrName : String,
    @SerializedName("category_group_code") var catGroupCode : String,
    @SerializedName("category_group_name") var catGroupName : String,
    @SerializedName("x") var x : String,
    @SerializedName("y") var y : String,
)