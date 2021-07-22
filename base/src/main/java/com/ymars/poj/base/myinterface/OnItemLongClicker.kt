package com.ymars.poj.base.myinterface

import android.view.View

open interface OnItemLongClicker<T> {
    fun onItemLongClick(v: View, data: T,pos:Int)
}