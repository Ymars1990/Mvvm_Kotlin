package com.ymars.poj.comutils


import android.util.Log;

/**
 * 日志打印类
 */
open class LogTools {
    companion object {
        fun e (tag:String? ,content:String?){
        if (KtStringUtils.isNotNullString(tag) && KtStringUtils.isNotNullString(content)) {
            Log.e(tag, content!!);
        } else if (!KtStringUtils.isNotNullString(tag)) {
            Log.e("Tag is null", content!!);
        } else {
            Log.e(tag, "content is null");
        }
    }

        fun d(tag:String? ,content:String?){
        if (KtStringUtils.isNotNullString(tag) && KtStringUtils.isNotNullString(content)) {
            Log.d(tag, content!!);
        } else if (!KtStringUtils.isNotNullString(tag)) {
            Log.d("Tag is null", content!!);
        } else {
            Log.d(tag, "content is null");
        }
    }

        fun i (tag:String? ,content:String?){
        if (KtStringUtils.isNotNullString(tag) && KtStringUtils.isNotNullString(content)) {
            Log.i(tag, content!!);
        } else if (!KtStringUtils.isNotNullString(tag)) {
            Log.i("Tag is null", content!!);
        } else {
            Log.i(tag, "content is null");
        }
    }

        fun w (tag:String? ,content:String?){
        if (KtStringUtils.isNotNullString(tag) && KtStringUtils.isNotNullString(content)) {
            Log.w(tag, content!!);
        } else if (!KtStringUtils.isNotNullString(tag)) {
            Log.w("Tag is null", content!!);
        } else {
            Log.w(tag, "content is null");
        }
    }

        fun v (tag:String? ,content:String?){
        if (KtStringUtils.isNotNullString(tag) && KtStringUtils.isNotNullString(content)) {
            Log.v(tag, content!!);
        } else if (!KtStringUtils.isNotNullString(tag)) {
            Log.v("Tag is null", content!!);
        } else {
            Log.v(tag, "content is null");
        }
    }
    }
}
