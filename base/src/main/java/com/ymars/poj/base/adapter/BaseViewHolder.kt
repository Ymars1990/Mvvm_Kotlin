package com.ymars.poj.base.adapter

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView


open class BaseViewHolder<T : ViewDataBinding>(var dataBinding: T) :
    RecyclerView.ViewHolder(dataBinding.root) {
}
