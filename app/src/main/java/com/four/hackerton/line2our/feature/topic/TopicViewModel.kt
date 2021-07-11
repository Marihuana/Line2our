package com.four.hackerton.line2our.feature.topic

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.four.hackerton.line2our.model.network.model.PlaceVO
import com.four.hackerton.line2our.model.network.repository.TopicRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import java.lang.Exception

class TopicViewModel : ViewModel() {
    var places = MutableLiveData<List<PlaceVO>>()
    var userCount = MutableLiveData<Int>()
    var errorMessage = MutableLiveData<String>()
    var adapter = PlaceListAdapter()

    fun getPlaceList(stationId : Int, topicId : Int){
        TopicRepository.getPlaces(stationId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnSuccess {
                if (it.result == "0000") {
                    it.data?.let { data ->
                        places.value = data.list
                        userCount.value = data.userCount
                    }
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