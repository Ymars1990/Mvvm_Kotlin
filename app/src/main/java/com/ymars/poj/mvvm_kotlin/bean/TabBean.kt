package com.ymars.poj.mvvm_kotlin.bean

class TabBean (var txt:String,var rid:Int,var netIcon:String,var state:Int){
    override fun toString(): String {
        return "TabBean(txt='$txt', rid=$rid, netIcon='$netIcon', state=$state)"
    }
}