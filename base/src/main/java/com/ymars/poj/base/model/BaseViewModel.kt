package com.ymars.poj.base.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ymars.network.base.BaseApiService
import com.ymars.network.base.BaseError
import com.ymars.network.base.BaseReponse
import com.ymars.poj.base.state.DataState
import com.ymars.poj.comutils.LogTools
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * @author Mars
 * MVVM  基类ViewModel
 */
open abstract class BaseViewModel<T : BaseApiService>(application: Application) :
    AndroidViewModel(application) {
    var errorData = MutableLiveData<BaseError>()//错误信息

    val TAG: String by lazy {
        this.javaClass.simpleName
    }
    val loadState by lazy { MutableLiveData<DataState>() }

    val apiService: T by lazy {
        initApiservice()
    }

    abstract fun initApiservice(): T

    fun <D> launch(
        block: suspend CoroutineScope.() -> BaseReponse<D>,//请求接口方法，T表示data实体泛型，调用时可将data对应的bean传入即可
        liveData: MutableLiveData<D>,
        reqUrl: String
    ) {
        viewModelScope.launch {
            try {
                val result = withContext(Dispatchers.IO) { block() }
                if (result.errorCode == 0) {//请求成功
                    liveData.postValue(result.data)
                    loadState.postValue(DataState.DataStateType.SUCCESS)
                } else {
                    loadState.postValue(DataState.DataStateType.ERROR)
                    errorData.postValue(BaseError(result.errorCode, result.errorMsg, reqUrl))
                }
            } catch (e: Throwable) {//接口请求失败
                LogTools.e(TAG, "请求异常>>" + e.message)
                loadState.postValue(DataState.DataStateType.NETWORK_ERROR)
                errorData.postValue(BaseError(0xE1, e.message, reqUrl))
            } finally {//请求结束
                LogTools.i(TAG, "请求结束Finally")
            }
        }
    }
}