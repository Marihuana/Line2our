package com.four.hackerton.line2our.model.network.service

import com.four.hackerton.line2our.model.network.model.Authorization
import com.four.hackerton.line2our.model.network.model.ResponseData
import com.four.hackerton.line2our.model.network.model.StationDetail
import com.four.hackerton.line2our.model.network.model.StationVO
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Query

interface StationService {

    /**
     *  역 정보
     */
    @GET("/station")
    fun getStationInfo(@Query("id") id : String) : Single<ResponseData<StationDetail>>
}