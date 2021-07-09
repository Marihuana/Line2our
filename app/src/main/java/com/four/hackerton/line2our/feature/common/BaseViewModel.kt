package com.four.hackerton.line2our.feature.common

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class BaseViewModel : ViewModel(){

//    protected fun <T> performResponse(observable : Single<T>, listener: (result: Result<T>) -> Unit) {
//        observable
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribeOn(Schedulers.io())
//            .doOnSuccess{
//                listener(Result.success(it))
//            }
//            .doOnError{
//                listener(Result.failure(convertThrowable(it)))
//            }
//            .subscribe()
//    }
}