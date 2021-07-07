package com.four.hackerton.line2our.model.network.service

import com.four.hackerton.line2our.model.network.model.SearchResult
import io.reactivex.rxjava3.core.Single
import retrofit2.Call
import retrofit2.http.*

interface TestService {
    /**
     * Get 방식에선 다음과 같이 선언
     */
    @GET("/v2/search/web")
    fun getSearchListOnWeb(@Query("query") keyWord : String, @Query("sort") sort : String, @Query("page") page : Int, @Query("size") size : Int) : Call<SearchResult>
    /**
     * Get 방식에선 다음과 같이 선언
     */
    @GET("/v2/search/web")
    fun getSearchListRx(@Query("query") keyWord : String, @Query("sort") sort : String, @Query("page") page : Int, @Query("size") size : Int) : Single<SearchResult>

    /**
     *  이렇게도 사용가능
     */
    @GET("/v2/search/web")
    fun getSearchListRx(@QueryMap query : Map<String, String>) : Single<SearchResult>

    /**
     * Post 방식으로 사용할 때
     */
    @POST("/v2/search/web")
    fun getSearchListWithPost(@Body param : Map<String, Any>)

    /**
     * 업데이트, patch 혹은 Put 사용
     */
    @PATCH("/v2/search/web")
    fun updateSearchResult(@Body param : Map<String, Any>)

    /**
     * 삭제
     */
    @DELETE("/v2/search/web")
    fun deleteSearchResult(@Body param : Map<String, Any>)


}