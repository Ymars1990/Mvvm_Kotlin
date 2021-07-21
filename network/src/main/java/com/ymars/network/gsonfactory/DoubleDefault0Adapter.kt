package com.ymars.network.gsonfactory

import com.google.gson.*
import java.lang.reflect.Type

/**
 * File descripition:   对返回值为空处理
 *
 * @author Administrator
 * @date 2018/5/21
 */
class DoubleDefault0Adapter : JsonSerializer<Double?>, JsonDeserializer<Double> {
    @Throws(JsonParseException::class)
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): Double {
        try {
            //定义为double类型,如果后台返回""或者null,则返回0.00
            if (json == null || json.asString == "" || json.asString == "null") {
                return 0.000000
            }
        } catch (ignore: Exception) {
        }
        return try {
            json.asDouble
        } catch (e: NumberFormatException) {
            throw JsonSyntaxException(e)
        }
    }

    override fun serialize(
        src: Double?,
        typeOfSrc: Type,
        context: JsonSerializationContext
    ): JsonElement {
        return JsonPrimitive(src)
    }
}