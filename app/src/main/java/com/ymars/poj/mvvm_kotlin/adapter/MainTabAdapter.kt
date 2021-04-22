package com.ymars.poj.mvvm_kotlin.adapter

import android.view.View
import com.ymars.poj.base.adapter.BaseAdapter
import com.ymars.poj.base.adapter.BaseViewHolder
import com.ymars.poj.comutils.LogTools
import com.ymars.poj.mvvm_kotlin.BR
import com.ymars.poj.mvvm_kotlin.R
import com.ymars.poj.mvvm_kotlin.bean.TabBean
import com.ymars.poj.mvvm_kotlin.databinding.MaintabItemBinding

class MainTabAdapter : BaseAdapter<TabBean, MaintabItemBinding>() {
    override fun onBindViewHolder(holder: BaseViewHolder<MaintabItemBinding>, position: Int) {
        holder.dataBinding.setVariable(BR.tabBean, data[position])
        holder.dataBinding.adapter = this
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.maintab_item
    }

    fun onItemClick(v: View, tabBean: TabBean) {
        LogTools.i(TAG, tabBean.toString())
        when (v.id) {
            R.id.tabItemLl -> {
                for (item: TabBean in data) {
                    if (item.pos == tabBean.pos) {
                        item.state = 1
                    } else {
                        item.state = 0
                    }
                }
                notifyDataSetChanged()
            }
        }
    }

}