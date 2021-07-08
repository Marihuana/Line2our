package com.four.hackerton.line2our.model.network.service

import com.four.hackerton.line2our.model.network.model.Authorization
import com.four.hackerton.line2our.model.network.model.ResponseData
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface TestService {

    /**
     *  Test용 URL
     */
    @POST("/auth/signin")
    fun requestLogin(@Body params : Map<String, String>) : Single<ResponseData<Authorization>>
}