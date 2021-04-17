package com.mars.splash.bean

import com.mars.network.base.BaseBean

class SplashAdBean : BaseBean() {
    var vertical: ArrayList<AdBean>? = null

    class AdBean() {
        var views: Int = 0
        var ncos: Int = 0
        var rank: Int = 0
        var source_type: String? = null
        var wp: String? = null
        var id: String? = null
        var desc: String? = null
        var thumb: String? = null
        var img: String? = null
        var preview: String? = null
        var store: String? = null
        override fun toString(): String {
            return "AdBean(views=$views, ncos=$ncos, rank=$rank, source_type=$source_type, wp=$wp, id=$id, desc=$desc, thumb=$thumb, img=$img, preview=$preview, store=$store)"
        }

    }

    override fun toString(): String {
        return "SplashAdBean(vertical=$vertical)"
    }

}