package com.mars.network.interceptor

import com.ymars.poj.base.Constant.Companion.BASE_SERVER_URL
import com.ymars.poj.comutils.KtStringUtils
import com.ymars.poj.comutils.LogTools
import okhttp3.*
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import java.io.IOException
import java.nio.charset.Charset


/**
 * @author Mars
 * 更换Url日志拦截器
 */
class FilterUrlInterceptor : Interceptor {
    private val UTF8: Charset = Charset.forName("UTF-8")
    private val TAG = this.javaClass.simpleName

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        //获取request

        //获取request
        val request = chain.request()
        //从request中获取原有的HttpUrl实例oldHttpUrl
        //从request中获取原有的HttpUrl实例oldHttpUrl
        val oldHttpUrl = request.url
        //获取request的创建者builder
        //获取request的创建者builder
        val builder: Request.Builder = request.newBuilder()
        //从request中获取headers，通过给定的键url_name
        //从request中获取headers，通过给定的键url_name
        val headerValues = request.headers("dynamics_url")

        if (headerValues != null && headerValues.size > 0) {
            //如果有这个header，先将配置的header删除，因此header仅用作app和okhttp之间使用
            builder.removeHeader("dynamics_url")
            //匹配获得新的BaseUrl
            val headerValue = headerValues[0]
            LogTools.i("headerValue", headerValue)
            var newBaseUrl: HttpUrl? = null
            newBaseUrl = if (KtStringUtils.isNotNullString(headerValue)) {
                headerValue.toHttpUrlOrNull()
            } else {
                BASE_SERVER_URL.toHttpUrlOrNull()
            }
            //重建新的HttpUrl，修改需要修改的url部分
            val newFullUrl = oldHttpUrl
                .newBuilder() // 更换网络协议
                .scheme(newBaseUrl!!.scheme) // 更换主机名
                .host(newBaseUrl.host) // 更换端口
                .port(newBaseUrl.port)
                .build()
            //重建这个request，通过builder.url(newFullUrl).build()；
            // 然后返回一个response至此结束修改
            LogTools.i("Url", "intercept: $newFullUrl")
            return chain.proceed(builder.url(newFullUrl).build())
        }
        return chain.proceed(request)
    }
}