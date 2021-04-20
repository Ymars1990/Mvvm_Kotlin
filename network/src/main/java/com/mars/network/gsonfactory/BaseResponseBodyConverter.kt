package com.mars.network.gsonfactory

import com.google.gson.Gson
import com.google.gson.TypeAdapter
import com.mars.network.base.BaseException
import com.mars.network.base.BaseReponseModel
import com.ymars.poj.base.Constant
import com.ymars.poj.comutils.LogTools
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Converter
import java.io.IOException
import java.nio.charset.Charset

class BaseResponseBodyConverter<T>(
    private val gson: Gson,
    private val adapter: TypeAdapter<T>
) : Converter<ResponseBody, T> {
    @Throws(IOException::class)
    override fun convert(value: ResponseBody): T? {
        val jsonString = value.string()
        LogTools.i(
            "BaseResponseBodyConverter",
            jsonString
        )
      val resp: BaseReponseModel<T>? =
          gson.fromJson(jsonString, BaseReponseModel::class.java) as BaseReponseModel<T>?;
        LogTools.i(
            "BaseResponseBodyConverter",
            resp.toString()
        )
        LogTools.i(
            "BaseResponseBodyConverter",
            resp!!.res.toString()
        )
        if(resp!!.code==0){
            val jsonObject = JSONObject(jsonString)
            val data: String = jsonObject.getString(Constant.data)
            return adapter.fromJson(data)
        }else{
            throw BaseException("数据解析错误", 0xE1);
        }
    }
    companion object {
        private val UTF_8 = Charset.forName("UTF-8")
    }
}