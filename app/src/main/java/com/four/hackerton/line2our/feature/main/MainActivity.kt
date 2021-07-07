package com.four.hackerton.line2our.feature.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.four.hackerton.line2our.R
import com.four.hackerton.line2our.feature.common.BaseActivity
import com.four.hackerton.line2our.model.network.RetrofitClient
import com.four.hackerton.line2our.model.network.model.SearchResult
import com.four.hackerton.line2our.model.network.repository.TestRepository
import com.four.hackerton.line2our.model.network.service.TestService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : BaseActivity() {
    private val tag = MainActivity::class.java.name

    var repository = TestRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //실제로 이렇게 사용되지는 않고 대부분 Coroutine 이나 RxAndroid 와 연동해서 결과를 받아옵니다
        val call = repository.searchOnDaum("해커톤")
        call.enqueue(object : Callback<SearchResult> {
            override fun onResponse(call: Call<SearchResult>, response: Response<SearchResult>) {
                //통신 성공했을 때
                if(response.isSuccessful){
                    response.body()?.let {
                        for(document in it.documents){
                            Log.d(tag, "result (title : ${document.title}, url : ${document.url}, content : ${document.contents}")
                        }
                    }
                }else{
                    //http error
                    Log.e(tag, "failed to connect")
                }

            }

            override fun onFailure(call: Call<SearchResult>, t: Throwable) {
                //통신 실패했을 때 (보통 네트워크 관련)
                Log.e(tag, "onFailure", t)
            }
        })

    }


}