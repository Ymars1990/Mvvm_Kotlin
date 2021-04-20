package com.mars.network.base

import androidx.lifecycle.MutableLiveData
import com.ymars.poj.base.BuildConfig
import com.ymars.poj.base.Constant
import com.ymars.poj.base.repository.BaseRepository
import com.ymars.poj.base.state.DataState
import com.ymars.poj.comutils.LogTools
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * @author Mars
 *基类观察者
 */
class BaseObserver<T>(
    private val liveData: MutableLiveData<T>,
    private val loadState: MutableLiveData<DataState>,
    private val repository: BaseRepository
) : Observer<T> {
    var TAG: String? = null

    init {
        TAG = this.javaClass.simpleName
    }

    override fun onComplete() {
    }

    override fun onSubscribe(d: Disposable) {
        repository.subscribe(d)
    }

    override fun onNext(response: T) {
        if (response != null) {
            LogTools.i(TAG, response.toString())
            loadState.postValue(DataState.DataStateType.SUCCESS)
            liveData.postValue(response)
        } else {
            LogTools.i(TAG, "数据为空")
            loadState.postValue(DataState.DataStateType.NULL)
        }
    }

    override fun onError(e: Throwable) {
        loadState.postValue(DataState.DataStateType.NETWORK_ERROR)
        if (BuildConfig.DEBUG) {
            e?.let {
                LogTools.i(TAG, e.message)
            }
        }
    }
}