package com.ymars.poj.base.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.ymars.poj.base.myinterface.OnItemClicker

abstract class BaseAdapter<T, V : ViewDataBinding> constructor(var data: ArrayList<T> = arrayListOf()) :
    RecyclerView.Adapter<BaseViewHolder<V>>() {
    lateinit var onItemClicker: OnItemClicker<T>

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

    fun addData(data: ArrayList<T>) {
        this.data.addAll(data)
        this.notifyDataSetChanged()
    }
}