package com.ymars.poj.base.`interface`

import android.view.View

open interface OnItemClicker<T> {
    fun onItemClick(v: View, data: T){}
}