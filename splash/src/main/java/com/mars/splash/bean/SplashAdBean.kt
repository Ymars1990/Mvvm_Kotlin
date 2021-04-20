package com.mars.splash.bean

data class SplashAdBean(var vertical: ArrayList<AdBean>) {
    data class AdBean(
        var views: Int,
        var ncos: Int,
        var rank: Int,
        var source_type: String,
        var wp: String,
        var id: String,
        var desc: String,
        var thumb: String,
        var img: String,
        var preview: String,
        var store: String
    ) {

        override fun toString(): String {
            return "AdBean(views=$views, ncos=$ncos, rank=$rank, source_type=$source_type, wp=$wp, id=$id, desc=$desc, thumb=$thumb, img=$img, preview=$preview, store=$store)"
        }

    }

    override fun toString(): String {
        return "SplashAdBean(vertical=$vertical)"
    }

}