package com.four.hackerton.line2our.model.network.repository

import com.four.hackerton.line2our.model.network.RetrofitClient
import com.four.hackerton.line2our.model.network.model.ResponseData
import com.four.hackerton.line2our.model.network.model.StationDetail
import com.four.hackerton.line2our.model.network.service.AuthService
import com.four.hackerton.line2our.model.network.service.StationService
import io.reactivex.rxjava3.core.Single

object StationRepository {
    private val service = RetrofitClient.create(StationService::class.java)

    fun getStationInfo(stationId : Int): Single<ResponseData<StationDetail>>{
        return service.getStationInfo(stationId.toString())
    }

}