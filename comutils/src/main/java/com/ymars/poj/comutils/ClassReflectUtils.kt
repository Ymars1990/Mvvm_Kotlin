package com.ymars.poj.comutils

import java.lang.reflect.ParameterizedType

/**
 * @author Mars
 * 反射工具类
 */
object ClassReflectUtils {
    fun <T> getClass(t: Any): Class<T> {
        // 通过反射 获取父类泛型 (T) 对应 Class类
        return (t.javaClass.genericSuperclass as ParameterizedType)
            .actualTypeArguments[0]
                as Class<T>
    }
}