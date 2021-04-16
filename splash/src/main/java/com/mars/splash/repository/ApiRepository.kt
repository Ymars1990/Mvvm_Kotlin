package com.mars.splash.repository

import com.mars.network.RetrofitManagerFactory
import com.ymars.poj.base.repository.BaseRepository

open class ApiRepository : BaseRepository() {

     val apiService: ApiService by lazy {
        RetrofitManagerFactory.instance.create(ApiService::class.java)
    }
}