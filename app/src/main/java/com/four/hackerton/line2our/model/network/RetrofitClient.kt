package com.four.hackerton.line2our.model.network

import com.four.hackerton.line2our.BuildConfig
import com.four.hackerton.line2our.global.RemoteConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    private var instance : Retrofit? = null
    private const val TIMEOUT_CONNECT = 60L
    private const val TIMEOUT_READ = 60L

   fun getInstance() : Retrofit{
       if(instance == null) {
           instance = Retrofit.Builder()
               .baseUrl(RemoteConfig.BASE_URL)                              //api 통신을 할 도메인
               .addConverterFactory(GsonConverterFactory.create())          //reponse 값을 json으로
               .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
               .client(createClient())
               .build()
       }
       return instance!!
   }

    private fun createClient() : OkHttpClient {

        return OkHttpClient.Builder()
            .addNetworkInterceptor(
                HttpLoggingInterceptor().apply {
                    level = if(RemoteConfig.IS_DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
                }
            )
            .addInterceptor {
                it.proceed(
                    it.request().run {
                        newBuilder().apply {
                            //token 있다면 header에 토큰 삽입하는 부분
    //                        if(PreferenceHelper.accessToken.isNotEmpty()) addHeader("Authorization", "Bearer ${PreferenceHelper.accessToken}")
                            addHeader("Authorization", "KakaoAK ${BuildConfig.KKAO_API_KEY}")
                            addHeader("Accept", "application/json")
                            addHeader("Content-Type", "application/json")
                        }.build()
                    }
                )
            }
            .connectTimeout(TIMEOUT_CONNECT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_READ, TimeUnit.SECONDS)
            .build()
    }
}