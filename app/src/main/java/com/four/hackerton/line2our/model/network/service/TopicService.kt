package com.four.hackerton.line2our.model.network.service

import com.four.hackerton.line2our.model.network.model.*
import io.reactivex.rxjava3.core.Single
import retrofit2.http.*

interface TopicService {

    /**
     *  위클리 토픽
     */
    @GET("/topic/weekly")
    fun getWeeklyTopic() : Single<ResponseData<WeeklyTopicVO>>

    /**
     *  카테고리별 토픽리스트
     */
    @GET("/topic/filter")
    fun getTopicList(@Query("station_id") stationId : String, @Query("category") category : String) : Single<ResponseData<List<TopicVO>>>

    /**
     *  토픽별 place 리스트
     */
    @GET("/topic/item")
    fun getPlaceList(@Query("topic_id") topicId : String) : Single<ResponseData<TopicItemData>>

    /**
     *  place 등록
     */
    @PUT("/topic/item")
    fun setPlace(@Body params : Map<String, String>) : Single<ResponseData<Unit>>


}