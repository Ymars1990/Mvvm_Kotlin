package com.ymars.poj.base.repository

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * @author Mars
 * 订阅事件
 */
open class BaseRepository {
    private val mCompositeSubscription by lazy { CompositeDisposable() }

    fun subscribe(disposable: Disposable) = mCompositeSubscription.add(disposable)

    fun unSubscribe() = mCompositeSubscription.dispose()
}