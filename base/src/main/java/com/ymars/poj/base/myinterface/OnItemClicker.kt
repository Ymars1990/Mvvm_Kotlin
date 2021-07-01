package com.ymars.poj.base.myinterface

import android.view.View

open interface OnItemClicker<T> {
    fun onItemClick(v: View, data: T,pos:Int)
}