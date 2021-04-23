package com.ymars.poj.base.ui

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.ymars.poj.comutils.LogTools
import java.lang.ref.WeakReference

abstract class BaseFragment<VB : ViewBinding> : Fragment() {
    lateinit var mCtx: Context
    lateinit var vb: VB

    val TAG: String by lazy {
        this.javaClass.simpleName
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mCtx = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vb = DataBindingUtil.inflate(inflater, setLayout(savedInstanceState), container, false)

        return vb.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initParams(savedInstanceState)
        doWork()
    }

    abstract fun setLayout(savedInstanceState: Bundle?): Int
    abstract fun doWork()
    abstract fun initParams(savedInstanceState: Bundle?)
    abstract fun handlerMsg(msg: Message)

    val handler: MyHandler by lazy {
        MyHandler(this)
    }

    companion object {
        class MyHandler(fragment: BaseFragment<*>) : Handler() {
            private val mFragment: WeakReference<BaseFragment<*>> =
                WeakReference(fragment)

            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
                LogTools.i(mFragment.get()!!.TAG, msg.what.toString())
                mFragment.get()!!.handlerMsg(msg)
            }
        }
    }
}