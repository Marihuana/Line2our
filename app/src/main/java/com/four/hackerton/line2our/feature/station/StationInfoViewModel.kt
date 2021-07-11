package com.four.hackerton.line2our.feature.station

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.four.hackerton.line2our.model.network.model.StationDetail
import com.four.hackerton.line2our.model.network.model.StationVO
import com.four.hackerton.line2our.model.network.model.TopicVO
import com.four.hackerton.line2our.model.network.model.WeeklyTopicVO
import com.four.hackerton.line2our.model.network.repository.StationRepository
import com.four.hackerton.line2our.model.network.repository.TopicRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import java.lang.Exception

class StationInfoViewModel : ViewModel() {
    var station = MutableLiveData<StationVO>()
    var badge = MutableLiveData<Boolean>()
    var topics = MutableLiveData<List<TopicVO>>()
    var errorMessage = MutableLiveData<String>()

    var activateCategory : Int = 0
    var adapter = StationTopicAdapter()
    var onSelectedCategoryListener : StationInfoHandler? = null


    fun getStationInfo(stationId : Int){
        StationRepository.getStationInfo(stationId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnSuccess {
                if (it.result == "0000") {
                    it.data?.let { data ->
                        station.value = data.station
                        badge.value = data.badge
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

    fun getTopics(stationId: Int, categoryId : Int){
        activateCategory = categoryId
        TopicRepository.getTopicList(stationId, categoryId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnSuccess {
                if (it.result == "0000") {
                    it.data?.let { data ->
                        topics.value = data
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

    fun next(){
        onSelectedCategoryListener?.nextStation()
    }

    fun prev(){
        onSelectedCategoryListener?.prevStation()
    }

    interface StationInfoHandler {
        fun onSelectedCategory(catId : Int)
        fun nextStation()
        fun prevStation()
    }
}