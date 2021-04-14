package com.ymars.poj.comutils

open class KtStringUtils {
    companion object {
        //对象转String
        fun objs2String(any: Any?): String {
            if (any == null) {
                return "Obj is Null"
            } else if (any is String) {
                return any
            } else {
                return any.toString()
            }
        }
        //String是否为空
        fun isNullString(obj: String?): Boolean {
            return obj == null || "" .equals( obj.replace(" ".toRegex(), ""))
        }

        //String是否不为空
        fun isNotNullString(obj: String?): Boolean {
            return !isNullString(obj)
        }
    }
}