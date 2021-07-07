package com.four.hackerton.line2our.model.network.common

import com.four.hackerton.line2our.global.CustomException
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

abstract class BaseRepository {
    abstract val service : Any

    protected fun <T> performResponse(observable : Single<T>, listener: (result: Result<T>) -> Unit) {
        observable
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnSuccess{
                listener(Result.success(it))
            }
            .doOnError{
                listener(Result.failure(convertThrowable(it)))
            }
            .subscribe()
    }

    private fun convertThrowable(code : String) : Throwable {
        //error 코드 작업
        return Throwable()
    }
    private fun convertThrowable(throwable: Throwable) : Throwable {
        //error 코드 작업
        return CustomException(throwable)
    }
}