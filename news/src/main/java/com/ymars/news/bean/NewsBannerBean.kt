package com.ymars.news.bean

data class NewsBannerBean(var desc:String,var id:Long,var imagePath:String,var isVisible:Int,var order:Int,var title:String,
var type:Int,var url:String) {
    override fun toString(): String {
        return "NewsBannerBean(desc='$desc', id=$id, imagePath='$imagePath', isVisible=$isVisible, order=$order, title='$title', type=$type, url='$url')"
    }
}