package com.four.hackerton.line2our.global

class RemoteConfig {
    companion object {
        var IS_DEBUG = true //BuildConfig.DEBUG
        const val BASE_URL : String = "http://1.234.27.134:1001" //물리서버
        const val KAKAO_API_URL : String = "https://dapi.kakao.com" //카카오 API를 예제로
    }
}