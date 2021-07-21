package com.ymars.network.interceptor

import com.ymars.poj.comutils.JsonFormater
import com.ymars.poj.comutils.LogTools
import okhttp3.*
import okio.Buffer
import java.io.IOException
import java.nio.charset.Charset
import java.nio.charset.UnsupportedCharsetException
import java.util.concurrent.TimeUnit


/**
 * @author Mars
 * 日志拦截器
 */
class LoggingInterceptor : Interceptor {
    private val UTF8: Charset = Charset.forName("UTF-8")
    private val TAG = this.javaClass.simpleName

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        //有请求body可以为null，此处requestBody 可以为null
        var requestBody: RequestBody? = request.body
        var reqBody: String? = null
        if (requestBody != null) {
            val buffer = Buffer()
            requestBody.writeTo(buffer)
            reqBody = buffer.readString(UTF8)
        }
        LogTools.Companion.i(
            TAG,
            String.format(
                "发送请求\nmethod：%s\nurl:%s\nheaders:%sbody:%s",
                request.method,
                request.url,
                request.headers,
                reqBody
            )
        )
        val startNs = System.nanoTime()
        val response = chain.proceed(request)
        val tookMs: Long =
            TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs)

        val responseBody = response.body
        val respBody: String

        val source = responseBody!!.source()
        source.request(java.lang.Long.MAX_VALUE)
        val buffer = source.buffer()

        respBody = buffer.clone().readString(UTF8)
        LogTools.Companion.i(
            TAG,
            String.format(
                "收到响应 %s %s %s ms\n请求url:%s\n请求body:%s\n请求header:%s响应body:%s",
                response.code,
                response.message,
                tookMs,
                response.request.url,
                reqBody,
                request.headers,
               JsonFormater.format(respBody),
            )
        )
        return response
    }
}