package com.four.hackerton.line2our.feature.main

import android.os.Bundle
import android.util.Log
import com.four.hackerton.line2our.R
import com.four.hackerton.line2our.feature.common.BaseActivity
import com.four.hackerton.line2our.model.network.repository.TestRepository


class MainActivity : BaseActivity() {
    private val tag = MainActivity::class.java.name
    private var repository = TestRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //이렇게 get 방식의 경우 query를 post 방식의 경우 parameter를 map 형태로 넣고 lamda로 Result를 받게 짜놨어요
        repository.searchOnRx(
            mapOf(
                Pair("query", "해커톤"),
                Pair("sort", "accuracy"),
                Pair("page", "1"),
                Pair("size", "20"))
        ){
            //여기서 성공여부 확인하시면 됩니다.
            if(it.isSuccess){
                for(document in it.getOrNull()?.documents ?: arrayListOf()){
                    Log.d(tag, "result (title : ${document.title}, url : ${document.url}, content : ${document.contents}")
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