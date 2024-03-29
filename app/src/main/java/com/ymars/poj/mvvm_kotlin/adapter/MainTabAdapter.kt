package com.ymars.poj.mvvm_kotlin.adapter

import androidx.core.content.ContextCompat
import com.ymars.poj.base.adapter.BaseAdapter
import com.ymars.poj.base.adapter.BaseViewHolder
import com.ymars.poj.mvvm_kotlin.BR
import com.ymars.poj.mvvm_kotlin.R
import com.ymars.poj.mvvm_kotlin.bean.TabBean
import com.ymars.poj.mvvm_kotlin.databinding.MaintabItemBinding
import com.ymars.poj.mvvm_kotlin.model.MainViewModel

class MainTabAdapter(var mVm: MainViewModel) : BaseAdapter<TabBean, MaintabItemBinding>() {
    override fun onBindViewHolder(holder: BaseViewHolder<MaintabItemBinding>, position: Int) {
        holder.dataBinding.setVariable(BR.tabBean, data[position])
        holder.dataBinding.mainVModel = mVm
        if(data[position].state==1){
            holder.dataBinding.tabTv.setTextColor(ContextCompat.getColor( holder.dataBinding.tabTv.context,R.color.app_theme_title_color))
        }else{
            holder.dataBinding.tabTv.setTextColor(ContextCompat.getColor( holder.dataBinding.tabTv.context,R.color.black))
        }
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.maintab_item
    }

}