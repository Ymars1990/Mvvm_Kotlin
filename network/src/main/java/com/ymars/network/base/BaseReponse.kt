package com.ymars.network.base

/**
 * @author Mars
 * 请求返回实体
 */
 data class BaseReponse<T>(var code: Int = -1, var msg: String = "", var res: T) {
    override fun toString(): String {
        return "*BaseReponse(code=$code, msg='$msg', res=$res)"
    }
}