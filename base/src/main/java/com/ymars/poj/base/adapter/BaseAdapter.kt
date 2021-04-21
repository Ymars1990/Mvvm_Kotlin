package com.ymars.poj.base.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T>(var data: ArrayList<T> = arrayListOf()) :
    RecyclerView.Adapter<BaseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
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
        if (data == null || data.size == 0) {
            return 0
        } else {
            return data.size
        }
    }

    fun refreshData(data: ArrayList<T>) {
        this.data = data
    }
}