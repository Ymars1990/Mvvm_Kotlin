package com.ymars.poj.base.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.ymars.poj.base.myinterface.OnItemClicker

abstract class BaseAdapter<T, V : ViewDataBinding> constructor(var data: ArrayList<T> = arrayListOf()) :
    RecyclerView.Adapter<BaseViewHolder<V>>() {
    lateinit var onItemClicker: OnItemClicker<T>
    lateinit var holder: BaseViewHolder<V>
    val TAG: String by lazy {
        this.javaClass.simpleName
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<V> {
        holder = BaseViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent?.context),
                viewType,
                parent,
                false
            )
        )
        return holder!!
    }

    override fun getItemCount(): Int {
        return if (data.size == 0) {
            0
        } else {
            data.size
        }
    }
    fun refreshData(data: MutableList<T>) {
        this.data.clear()
        this.data.addAll(data)
        this.notifyDataSetChanged()
    }

    fun addData(data: MutableList<T>) {
        this.data.addAll(data)
        this.notifyDataSetChanged()
    }
}