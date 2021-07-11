package com.four.hackerton.line2our.model.network.service

import com.four.hackerton.line2our.model.network.model.*
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MyService {

    /**
     *  내가 등록한 장소 (토픽)
     */
    @GET("/my/myTopicItem")
    fun getAddedPlaces(@Query("topic_id") topicId : String) : Single<ResponseData<List<PlaceVO>>>
    /**
     *  내가 등록한 장소 (토픽)
     */
    @GET("/my/myTopic")
    fun getAddedTopic() : Single<ResponseData<List<TopicVO>>>

    /**
     *  내가 저장한 장소 리스트
     */
    @GET("/bookmark/list")
    fun getBookmarkList() : Single<ResponseData<List<PlaceVO>>>
}