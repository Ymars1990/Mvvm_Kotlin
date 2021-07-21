package com.ymars.network.base

data class BaseError(var code: Int, var errorMsg: String?, var reqUrl: String){
    override fun toString(): String {
        return "BaseError(code=$code, errorMsg='$errorMsg', reqUrl='$reqUrl')"
    }
}
