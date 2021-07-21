package com.ymars.poj.base.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.ymars.poj.base.R
import com.ymars.poj.comutils.LogTools

class ImageAdapter {


    companion object {
        val TAG: String by lazy {
            this.javaClass.simpleName
        }

        @BindingAdapter("app:img_res")
        @JvmStatic
        fun setSrcByImgRes(view: ImageView, img_url: Int) {
            Glide.with(view.context)
                .load(img_url)
                .placeholder(R.mipmap.ic_default)
                .error(R.mipmap.ic_default)
                .into(view)
        }

        @BindingAdapter("app:img_url")
        @JvmStatic
        fun setSrcByImgUrl(view: ImageView, img_url: String) {
            Glide.with(view.context)
                .load(img_url)
                .placeholder(R.mipmap.ic_default)
                .error(R.mipmap.ic_default)
                .into(view)
        }
    }
}