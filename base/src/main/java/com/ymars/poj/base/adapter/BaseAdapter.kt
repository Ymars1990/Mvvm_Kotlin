package com.ymars.poj.base.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T,V:ViewDataBinding>(var data: ArrayList<T> = arrayListOf()) :
    RecyclerView.Adapter<BaseViewHolder<V>>() {
    val TAG: String by lazy {
        this.javaClass.simpleName
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<V> {
        return BaseViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent?.context),
                viewType,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return if (data.size == 0) {
            0
        } else {
            data.size
        }
    }

    fun refreshData(data: ArrayList<T>) {
        this.data.clear()
        this.data.addAll(data)
        this.notifyDataSetChanged()
    }
}