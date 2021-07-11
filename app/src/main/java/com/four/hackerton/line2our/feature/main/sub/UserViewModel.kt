package com.four.hackerton.line2our.feature.main.sub

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.four.hackerton.line2our.feature.station.StationTopicAdapter
import com.four.hackerton.line2our.feature.topic.PlaceListAdapter
import com.four.hackerton.line2our.model.network.model.PlaceVO
import com.four.hackerton.line2our.model.network.model.TopicVO
import com.four.hackerton.line2our.model.network.repository.MyRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import java.lang.Exception

class UserViewModel : ViewModel(){
    var bookmarks = MutableLiveData<List<PlaceVO>>()
    var topics = MutableLiveData<List<TopicVO>>()
    var errorMessage = MutableLiveData<String>()
    var topicsAdapter = StationTopicAdapter()
    var placesAdapter = PlaceListAdapter()

    fun getBookMarkList(){
        MyRepository.getBookmarkList()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnSuccess {
                if (it.result == "0000") {
                    it.data?.let { data ->
                        bookmarks.value =  data
                        placesAdapter.items.clear()
                        placesAdapter.items.addAll(data)
                        placesAdapter.notifyDataSetChanged()
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

    fun getAddedTopic(){
        MyRepository.getAddedTopic()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnSuccess {
                if (it.result == "0000") {
                    it.data?.let { data ->
                        topics.value =  data
                        topicsAdapter.items.clear()
                        topicsAdapter.items.addAll(data)
                        topicsAdapter.notifyDataSetChanged()
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

    fun getAddedPlaces(topicId : Int){
        MyRepository.getAddedPlaces(topicId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnSuccess {
                if (it.result == "0000") {
                    it.data?.let { data ->
                        bookmarks.value =  data
                        placesAdapter.items.clear()
                        placesAdapter.items.addAll(data)
                        placesAdapter.notifyDataSetChanged()
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