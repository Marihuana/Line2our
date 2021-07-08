package com.four.hackerton.line2our.model.network.repository

import com.four.hackerton.line2our.model.network.RetrofitClient
import com.four.hackerton.line2our.model.network.common.BaseRepository
import com.four.hackerton.line2our.model.network.model.Authorization
import com.four.hackerton.line2our.model.network.model.ResponseData
import com.four.hackerton.line2our.model.network.service.TestService

class TestRepository : BaseRepository() {
    override val service : TestService
        get() = RetrofitClient.create(TestService::class.java)


    fun login(query : Map<String, String>, listener: (result: Result<ResponseData<Authorization>>) -> Unit){
        super.performResponse(service.requestLogin(query), listener)
    }
}