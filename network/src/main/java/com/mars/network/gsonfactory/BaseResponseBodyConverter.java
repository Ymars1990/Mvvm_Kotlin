package com.mars.network.gsonfactory;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import com.mars.network.base.BaseException;
import com.ymars.poj.base.Constant;
import com.ymars.poj.comutils.KtStringUtils;
import com.ymars.poj.comutils.LogTools;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * @author Mars
 * 时间：2019/08/23
 * 描述：请求返回基类转化工厂
 */

public class BaseResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private Gson gson;
    private TypeAdapter<T> adapter;

    BaseResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String jsonString = value.string();
        LogTools.Companion.i("value", KtStringUtils.Companion.isNotNullString(jsonString) ? jsonString : "is null");
        try {
            JSONObject object = new JSONObject(jsonString);
            int status = object.getInt(Constant.code);
            if (status != 0) {
                if (status == -1001) {
                     LogTools.Companion.i("当前状态", "当前状态未登录");
                }
                String msg = object.getString(Constant.msg);
                if (TextUtils.isEmpty(msg)) {
                    msg = String.format("[%s]", status);
                }
                //异常处理
                throw new BaseException(msg, status);
            } else {
                String data = object.getString(Constant.data);
                if (KtStringUtils.Companion.isNotNullString(data)) {
                     LogTools.Companion.i("Data", data);
                    return adapter.fromJson(data);
                } else {
                     LogTools.Companion.i("Data", "data is null");
                    return adapter.fromJson("{}");
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
            //数据解析异常
             LogTools.Companion.i(this.getClass().getSimpleName(), e.getMessage());
            throw new BaseException(BaseException.PARSE_ERROR_MSG, BaseException.PARSE_ERROR);
        } finally {
            value.close();
        }
    }
}
