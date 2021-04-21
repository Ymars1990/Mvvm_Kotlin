package com.ymars.poj.mvvm_kotlin.adapter

import android.view.View
import androidx.databinding.ViewDataBinding
import com.ymars.poj.base.adapter.BaseAdapter
import com.ymars.poj.base.adapter.BaseViewHolder
import com.ymars.poj.mvvm_kotlin.BR
import com.ymars.poj.mvvm_kotlin.R
import com.ymars.poj.mvvm_kotlin.bean.TabBean

class MainTabAdapter : BaseAdapter<TabBean>() {
    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        var binding: ViewDataBinding = holder.dataBinding
        binding.setVariable(BR.tabBean, data[position])
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.maintab_item
    }

    fun onItemClick(v: View,position: Int) {
        for (item: TabBean in data) {
            if (item.state == 1) {
                item.state = 0
            }
        }
    }

}