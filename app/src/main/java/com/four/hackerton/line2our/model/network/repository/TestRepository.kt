package com.four.hackerton.line2our.model.network.repository

import com.four.hackerton.line2our.model.network.RetrofitClient
import com.four.hackerton.line2our.model.network.model.SearchResult
import com.four.hackerton.line2our.model.network.service.TestService
import retrofit2.Call

class TestRepository {
    private val service : TestService = RetrofitClient.getInstance().create(TestService::class.java)

    fun searchOnDaum(keyword : String, sort : String = "accuracy", page : Int = 1, size : Int = 20) : Call<SearchResult>{
        return service.getSearchListOnWeb(keyword, sort, page, size)
    }
}