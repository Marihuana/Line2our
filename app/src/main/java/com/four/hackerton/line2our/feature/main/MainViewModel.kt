package com.four.hackerton.line2our.feature.main

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.four.hackerton.line2our.model.network.model.WeeklyTopicVO
import com.four.hackerton.line2our.model.network.repository.TopicRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import java.lang.Exception

class MainViewModel : ViewModel(){
    var weeklyTopic = MutableLiveData<WeeklyTopicVO>()
    var errorMessage = MutableLiveData<String>()

    fun getWeeklyTopic(){
        TopicRepository.getWeeklyTopic()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnSuccess {
                if (it.result == "0000") {
                    it.data?.let { data -> weeklyTopic.value =  data}
                } else {
                    errorMessage.value = it.message
                }
            }
            .doOnError {
                try { errorMessage.value = it.message
                } catch (e : Exception) {}

            }
            .subscribe()
    }

}