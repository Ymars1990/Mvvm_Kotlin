package com.ymars.poj.mvvm_kotlin.repository

import androidx.lifecycle.MutableLiveData
import com.ymars.poj.base.state.DataState

open class MainRepository(private val loadState: MutableLiveData<DataState>) : ApiRepository() {


}
