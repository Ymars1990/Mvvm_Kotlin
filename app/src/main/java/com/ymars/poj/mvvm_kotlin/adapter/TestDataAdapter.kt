package com.ymars.poj.mvvm_kotlin.adapter

import com.mars.splash.bean.SplashAdBean
import com.ymars.poj.base.adapter.BaseAdapter
import com.ymars.poj.base.adapter.BaseViewHolder
import com.ymars.poj.mvvm_kotlin.BR
import com.ymars.poj.mvvm_kotlin.R
import com.ymars.poj.mvvm_kotlin.databinding.TestrvItemBinding
import com.ymars.poj.mvvm_kotlin.model.TestViewModel

class TestDataAdapter(var mVm: TestViewModel) :
    BaseAdapter<SplashAdBean.AdBean, TestrvItemBinding>() {
    override fun onBindViewHolder(holder: BaseViewHolder<TestrvItemBinding>, position: Int) {
        holder.dataBinding.setVariable(BR.testDataBean, data[position])
        holder.dataBinding.testDataModel = mVm
        holder.dataBinding.root.setOnClickListener {

        }
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.testrv_item
    }
}