package com.ymars.poj.base.ui

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.ymars.poj.comutils.LogTools

class MyLifecycleObserver(var tag: String):DefaultLifecycleObserver {
    
    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        LogTools.i(tag, "onCreate")
    }

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        LogTools.i(tag, "onResume")
    }

    override fun onPause(owner: LifecycleOwner) {
        super.onPause(owner)
        LogTools.i(tag, "onPause")
    }

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
        LogTools.i(tag, "onStart")
    }

    override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)
        LogTools.i(tag, "onStop")
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        LogTools.i(tag, "onDestroy")
    }
}