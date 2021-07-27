package com.ymars.poj.mvvm_kotlin.ui

import android.os.Bundle
import android.os.Message
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.bumptech.glide.Glide
import com.mars.splash.bean.SplashAdBean
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener
import com.ymars.news.ui.fragment.NewsMainFragment
import com.ymars.poj.base.myinterface.OnItemClicker
import com.ymars.poj.base.ui.LifecyclerFragment
import com.ymars.poj.comutils.LogTools
import com.ymars.poj.mvvm_kotlin.R
import com.ymars.poj.mvvm_kotlin.adapter.TestDataAdapter
import com.ymars.poj.mvvm_kotlin.databinding.FragmentTestBinding
import com.ymars.poj.mvvm_kotlin.model.TestViewModel

class TestFragment :
    LifecyclerFragment<TestViewModel, FragmentTestBinding>() {
    var adapter: TestDataAdapter? = null

    companion object {
        fun newInstance(tag: String) = TestFragment().apply {
            var arguments = Bundle(1).apply {
                putString("tag", tag)
            }
        }

    }

    override fun setLayout(savedInstanceState: Bundle?): Int {
        return R.layout.fragment_test
    }

    override fun doWork() {

    }

    override fun handlerMsg(msg: Message) {

    }


}
