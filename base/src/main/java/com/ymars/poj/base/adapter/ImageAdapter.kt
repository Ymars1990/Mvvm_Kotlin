package com.ymars.poj.base.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.ymars.poj.base.R

class ImageAdapter {
    companion object {
        @BindingAdapter("app:img_url")
        @JvmStatic
        fun setSrcByImgRes(view: ImageView, img_url: String) {
            Glide.with(view.context)
                .load(img_url)
                .placeholder(R.mipmap.ic_default)
                .error(R.mipmap.ic_default)
                .into(view)
        }

    }
}