package com.four.hackerton.line2our.feature.main

import android.os.Bundle
import android.util.Log
import com.four.hackerton.line2our.R
import com.four.hackerton.line2our.feature.common.BaseActivity
import com.four.hackerton.line2our.model.network.repository.KakaoRepository
import com.four.hackerton.line2our.model.network.repository.TestRepository


class MainActivity : BaseActivity() {
    private val tag = MainActivity::class.java.name
    private var repository = TestRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //이렇게 get 방식의 경우 query를 post 방식의 경우 parameter를 map 형태로 넣고 lamda로 Result를 받게 짜놨어요
        repository.login(
            mapOf(
                Pair("email", "test@naver.com"),
                Pair("password", "1234"))
        ){
            //여기서 성공여부 확인하시면 됩니다.
            if(it.isSuccess){
                it.getOrNull()?.let { response ->
                    when(response.result){
                        "0000" -> {
                            var user = response.data!!.user
                            var token = response.data!!.token
                            Log.d(tag, "result (email : ${user.email}, token : ${token.accesToken}")
                        }
                        else -> { }
                    }
                }
            }else{
                //실패시 실패 처리 하는 함수 만드셔서 토스트 처리해도 되고 기획이 따로 있으면 기획에 따라가면 됩니다.
                processError(it.exceptionOrNull())
            }
        }

        var kakao = KakaoRepository()
        kakao.searchPlace(
            KakaoRepository.ParametersBuilder().apply {
                this.keyword = "스타벅스"
            }.build()
        ){
            if(it.isSuccess){
                it.getOrNull()?.let { response ->
                    for(place in response.places){
                        Log.d(tag, "result (name : ${place.name}, url : ${place.url}")
                    }
                }
            }else{
                //실패시 실패 처리 하는 함수 만드셔서 토스트 처리해도 되고 기획이 따로 있으면 기획에 따라가면 됩니다.
                processError(it.exceptionOrNull())
            }
        }

        //region useless codes
        //실제로 이렇게 사용되지는 않고 대부분 Coroutine 이나 RxAndroid 와 연동해서 결과를 받아옵니다
//        val call = repository.searchOnDaum("해커톤")
//        call.enqueue(object : Callback<SearchResult> {
//            override fun onResponse(call: Call<SearchResult>, response: Response<SearchResult>) {
//                //통신 성공했을 때
//                if(response.isSuccessful){
//                    response.body()?.let {
//                        for(document in it.documents){
//                            Log.d(tag, "result (title : ${document.title}, url : ${document.url}, content : ${document.contents}")
//                        }
//                    }
//                }else{
//                    //http error
//                    Log.e(tag, "failed to connect")
//                }
//
//            }
//
//            override fun onFailure(call: Call<SearchResult>, t: Throwable) {
//                //통신 실패했을 때 (보통 네트워크 관련)
//                Log.e(tag, "onFailure", t)
//            }
//        })
        //endregion
    }


    private fun processError(throwable : Throwable?){
        Log.e(tag, "error", throwable)
    }
}