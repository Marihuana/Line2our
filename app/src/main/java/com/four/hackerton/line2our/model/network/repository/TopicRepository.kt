package com.four.hackerton.line2our.model.network.repository

import com.four.hackerton.line2our.model.network.RetrofitClient
import com.four.hackerton.line2our.model.network.common.BaseRepository
import com.four.hackerton.line2our.model.network.model.*
import com.four.hackerton.line2our.model.network.service.AuthService
import com.four.hackerton.line2our.model.network.service.TopicService
import io.reactivex.rxjava3.core.Single

object TopicRepository {
    private val service = RetrofitClient.create(TopicService::class.java)

    fun getWeeklyTopic() : Single<ResponseData<WeeklyTopicVO>> {
        return service.getWeeklyTopic()
    }

    fun getTopicList(stationId : Int, catId : Int) : Single<ResponseData<List<TopicVO>>> {
        return service.getTopicList(stationId.toString(), catId.toString())
    }

    fun getPlaces(topicId: Int) : Single<ResponseData<TopicItemData>>{
        return service.getPlaceList(topicId.toString())
    }

    fun setPlace(topicId : Int, place : Place) : Single<ResponseData<Unit>>{
        mapOf(
            Pair("topic_id", topicId.toString()),
            Pair("place_url", place.url),
            Pair("place_name", place.name),
            Pair("address_name", place.addressName),
            Pair("phone", place.phone),
            Pair("latitude", place.x),
            Pair("longitude", place.y),
            Pair("distance", place.distance)
        ).let {
            return service.setPlace(it)
        }
    }
}