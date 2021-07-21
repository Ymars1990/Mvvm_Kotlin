package com.ymars.network.gsonfactory

import com.google.gson.*
import java.lang.reflect.Type

/**
 * File descripition:double=>
 *
 * @author Administrator    对返回值为空处理
 * @date 2018/5/21
 */
class IntegerDefault0Adapter : JsonSerializer<Int?>, JsonDeserializer<Int> {
    @Throws(JsonParseException::class)
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): Int {
        try {
            if (json == null || json.asString == "" || json.asString == "null") { //定义为int类型,如果后台返回""或者null,则返回0
                return 0
            }
        } catch (ignore: Exception) {
        }
        return try {
            json.asInt
        } catch (e: NumberFormatException) {
            throw JsonSyntaxException(e)
        }
    }

    override fun serialize(
        src: Int?,
        typeOfSrc: Type,
        context: JsonSerializationContext
    ): JsonElement {
        return JsonPrimitive(src)
    }
}