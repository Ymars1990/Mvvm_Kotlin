package com.mars.network.base

/**
 * @author Mars
 * 请求返回实体
 */
 data class BaseReponseModel<T>(var code: Int = -1, var msg: String = "", var res: T) {
    override fun toString(): String {
        return "BaseReponseModel(code=$code, msg='$msg', res=$res)"
    }
}