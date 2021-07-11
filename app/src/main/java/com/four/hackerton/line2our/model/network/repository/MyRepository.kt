package com.four.hackerton.line2our.model.network.repository

import com.four.hackerton.line2our.model.network.RetrofitClient
import com.four.hackerton.line2our.model.network.model.MyTopicVO
import com.four.hackerton.line2our.model.network.model.PlaceVO
import com.four.hackerton.line2our.model.network.model.ResponseData
import com.four.hackerton.line2our.model.network.model.TopicVO
import com.four.hackerton.line2our.model.network.service.AuthService
import com.four.hackerton.line2our.model.network.service.MyService
import io.reactivex.rxjava3.core.Single

object MyRepository {
    private val service = RetrofitClient.create(MyService::class.java)

    fun getAddedPlaces(topicId : Int) : Single<ResponseData<List<PlaceVO>>> {
       return service.getAddedPlaces(topicId.toString())
    }

    fun getAddedTopic() : Single<ResponseData<List<TopicVO>>> {
       return service.getAddedTopic()
    }

    fun getBookmarkList() : Single<ResponseData<List<PlaceVO>>> {
       return service.getBookmarkList()
    }
}