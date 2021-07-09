package com.four.hackerton.line2our.feature.main

import android.os.Bundle
import android.util.Log
import android.view.ScaleGestureDetector
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.four.hackerton.line2our.R
import com.four.hackerton.line2our.feature.common.BaseActivity
import com.four.hackerton.line2our.feature.main.sub.HomeFragment
import com.four.hackerton.line2our.feature.main.sub.UserFragment
import com.four.hackerton.line2our.model.network.repository.KakaoRepository
import com.four.hackerton.line2our.model.network.repository.TestRepository
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity(), ScaleGestureDetector.OnScaleGestureListener {
    private val tag = MainActivity::class.java.name
    var activeFragment : Fragment = HomeFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().apply {
            this.replace(container.id, HomeFragment)
            this.commitAllowingStateLoss()
        }

        bnvMain.setOnItemSelectedListener{ item ->
            changeTab(item.itemId)
            true
        }
    }

    private fun changeTab(id: Int) {
        val selected = getTabFragment(id)

        if(selected == activeFragment) return

        supportFragmentManager.beginTransaction().apply {
            this.replace(container.id, selected)
            this.commit()
            activeFragment = selected
        }
    }

    private fun getTabFragment(id: Int): Fragment {
        return when(id) {
            R.id.nav_home -> HomeFragment
            R.id.nav_user -> UserFragment
            else -> HomeFragment
        }
    }

    override fun onScale(detector: ScaleGestureDetector?): Boolean {
        TODO("Not yet implemented")
    }

    override fun onScaleBegin(detector: ScaleGestureDetector?): Boolean {
        TODO("Not yet implemented")
    }

    override fun onScaleEnd(detector: ScaleGestureDetector?) {
        TODO("Not yet implemented")
    }

    /*
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
     */
}