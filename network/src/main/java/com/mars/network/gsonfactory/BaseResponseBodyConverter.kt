package com.mars.network.gsonfactory

import com.google.gson.Gson
import com.google.gson.JsonIOException
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonToken
import okhttp3.ResponseBody
import retrofit2.Converter
import java.io.IOException

class BaseResponseBodyConverter<T>(
      val gson: Gson,
    val adapter: TypeAdapter<T>
) : Converter<ResponseBody, T> {
    @Throws(IOException::class)
    override fun convert(value: ResponseBody): T? {
        // 在这里通过 value 拿到 json 字符串进行解析
        // 判断状态码是失败的情况，就抛出异常
        val jsonReader = gson.newJsonReader(value.charStream())
        value.use {
            val result = adapter.read(jsonReader)
            if (jsonReader.peek() != JsonToken.END_DOCUMENT) {
                throw JsonIOException("JSON document was not fully consumed.")
            }
            return result
        }
    }
}