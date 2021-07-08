package com.four.hackerton.line2our.model.network.repository

import com.four.hackerton.line2our.global.RemoteConfig
import com.four.hackerton.line2our.model.network.RetrofitClient
import com.four.hackerton.line2our.model.network.common.BaseRepository
import com.four.hackerton.line2our.model.network.model.KakaoResponse
import com.four.hackerton.line2our.model.network.service.KakaoApiService
import retrofit2.Call

class KakaoRepository : BaseRepository() {
    override val service : KakaoApiService
        get() = RetrofitClient.create(RemoteConfig.KAKAO_API_URL, KakaoApiService::class.java)

    /**
     *  param ->
     *  query : 검색을 원하는 질의어
     *  category_group_code : 카테고리 그룹 코드, 결과를 카테고리로 필터링을 원하는 경우 사용
     *  x : 중심 좌표의 X값 혹은 longitude. 특정 지역을 중심으로 검색하려고 할 경우 radius와 함께 사용 가능
     *  y : 중심 좌표의 Y값 혹은 latitude. 특정 지역을 중심으로 검색하려고 할 경우 radius와 함께 사용 가능
     *  radius : 중심 좌표부터의 반경거리. 특정 지역을 중심으로 검색하려고 할 경우 중심좌표로 쓰일 x,y와 함께 사용. 단위 meter, 0~20000 사이의 값
     *  rect : 사각형 범위내에서 제한 검색을 위한 좌표. 지도 화면 내 검색시 등 제한 검색에서 사용 가능. 좌측 X 좌표,좌측 Y 좌표, 우측 X 좌표, 우측 Y 좌표 형식
     *  page : 결과 페이지 번호. 1~45 사이의 값 (기본값: 1)
     *  size : 한 페이지에 보여질 문서의 개수 1~15 사이의 값 (기본값: 15)
     *  sort : 결과 정렬 순서, distance 정렬을 원할 때는 기준 좌표로 쓰일 x, y와 함께 사용. distance 또는 accuracy (기본값: accuracy)
     */


    fun searchPlace(keyword : String, sort : String = "accuracy", page : Int = 1, size : Int = 20, listener: (result: Result<KakaoResponse>) -> Unit){
        ParametersBuilder().apply {
            this.keyword = keyword
            this.sort = sort
            this.page = page
            this.size = size
        }.build().let {
            super.performResponse(service.getPlaceList(it), listener)
        }
    }

    fun searchPlace(query : Map<String, String>, listener: (result: Result<KakaoResponse>) -> Unit){
        super.performResponse(service.getPlaceList(query), listener)
    }

    open class ParametersBuilder{
        lateinit var keyword : String
        var groupCode : String? = null
        var x : String? = null
        var y : String? = null
        var radius : Int? = null
        var rect : String? = null
        var page : Int? = null
        var size : Int = 15
        var sort : String = "accuracy"

        fun build() : Map<String, String>{
            return mapOf<String, String>(
                Pair("query", keyword),
                Pair("groupCode", groupCode ?: ""),
                Pair("x", x ?: ""),
                Pair("y", y ?: ""),
                Pair("radius", radius?.toString() ?: ""),
                Pair("rect", rect ?: ""),
                Pair("page", page?.toString() ?: ""),
                Pair("size", size.toString()),
                Pair("sort", sort),
            )
        }
    }
}