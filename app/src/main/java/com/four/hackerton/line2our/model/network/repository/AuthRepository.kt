package com.four.hackerton.line2our.model.network.repository

import com.four.hackerton.line2our.model.network.RetrofitClient
import com.four.hackerton.line2our.model.network.common.BaseRepository
import com.four.hackerton.line2our.model.network.model.Authorization
import com.four.hackerton.line2our.model.network.model.ResponseData
import com.four.hackerton.line2our.model.network.service.AuthService
import io.reactivex.rxjava3.core.Single

class AuthRepository {
    private val service = RetrofitClient.create(AuthService::class.java)

    fun login(query : Map<String, String>) : Single<ResponseData<Authorization>>{
        return service.requestLogin(query)
    }
}