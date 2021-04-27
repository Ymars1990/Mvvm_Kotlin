package com.ymars.poj.base.ui

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.ymars.poj.comutils.LogTools
import java.lang.ref.WeakReference

/**
 * @author Mars
 * 基类Activity
 */
abstract class BaseActivity : AppCompatActivity() {
    lateinit var mCtx: Context

    val TAG: String by lazy {
        this.javaClass.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mCtx = this
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        initParams(savedInstanceState)
        doWork()
    }

    override fun onDestroy() {
        super.onDestroy()
        handler!!.removeCallbacksAndMessages(null)
    }

    abstract fun setLayout(savedInstanceState: Bundle?): Int
    abstract fun doWork()
    abstract fun initParams(savedInstanceState: Bundle?)
    abstract fun handlerMsg(msg: Message)

    val handler: MyHandler by lazy {
        MyHandler(this)
    }

    companion object {
        class MyHandler(activity: BaseActivity) : Handler() {
            private val mActivity: WeakReference<BaseActivity> =
                WeakReference(activity)

            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
                LogTools.i( mActivity.get()!!.TAG,msg.what.toString())
                mActivity.get()!!.handlerMsg(msg)
            }
        }
    }
}
