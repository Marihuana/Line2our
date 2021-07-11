package com.four.hackerton.line2our.feature.intro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import com.four.hackerton.line2our.R
import com.four.hackerton.line2our.feature.main.MainActivity
import com.four.hackerton.line2our.global.SharedPreferenceHelper
import com.four.hackerton.line2our.model.network.repository.AuthRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        mapOf(
            Pair("email", "test@naver.com"),
            Pair("password", "1234")
        ).let { param ->
            AuthRepository().login(param)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnSuccess {
                    if (it.result == "0000") {
                        it.data?.token?.accesToken?.let { token ->
                            SharedPreferenceHelper.accessToken = token
                            object : CountDownTimer(1500L, 1000L) {
                                override fun onTick(millisUntilFinished: Long) {}
                                override fun onFinish() {
                                    startActivity(
                                        Intent(
                                            applicationContext,
                                            MainActivity::class.java
                                        )
                                    )
                                    finish()
                                }
                            }.start()
                        }
                    } else {
                        Toast.makeText(applicationContext, it.message, Toast.LENGTH_SHORT).show()
                    }
                }
                .doOnError {
                    Toast.makeText(applicationContext, it.message, Toast.LENGTH_SHORT).show()
                }
                .subscribe()
        }
    }
}