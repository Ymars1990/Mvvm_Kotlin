package com.ymars.network.base

/**
 * @author Mars
 * 请求返回实体
 */
 data class BaseReponse<T>(var errorCode: Int = -1, var errorMsg: String = "", var data: T) {
    override fun toString(): String {
        return "*BaseReponse(errorCode=$errorCode, errorMsg='$errorMsg', data=$data)"
    }
}