package com.four.hackerton.line2our.model.network.repository

import com.four.hackerton.line2our.model.network.RetrofitClient
import com.four.hackerton.line2our.model.network.common.BaseRepository
import com.four.hackerton.line2our.model.network.model.SearchResult
import com.four.hackerton.line2our.model.network.service.TestService
import io.reactivex.rxjava3.core.Single
import retrofit2.Call

class TestRepository : BaseRepository() {
    override val service : TestService
        get() = RetrofitClient.getInstance().create(TestService::class.java)

    fun searchOnDaum(keyword : String, sort : String = "accuracy", page : Int = 1, size : Int = 20) : Call<SearchResult>{
        return service.getSearchListOnWeb(keyword, sort, page, size)
    }

    fun searchOnRx(query : Map<String, String>, listener: (result: Result<SearchResult>) -> Unit){
        super.performResponse(service.getSearchListRx(query), listener)
    }
}