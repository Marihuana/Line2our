package com.four.hackerton.line2our.model.network.service

import com.four.hackerton.line2our.model.network.model.KakaoResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.*

interface KakaoApiService {
    /**
     *  카카오 장소 검색
     */
    @GET("/v2/local/search/keyword.json")
    fun getPlaceList(@QueryMap query : Map<String, String>) : Single<KakaoResponse>


}