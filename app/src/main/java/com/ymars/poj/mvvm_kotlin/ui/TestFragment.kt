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
    LifecyclerFragment<TestViewModel, FragmentTestBinding>(), OnItemClicker<SplashAdBean.AdBean>,
    OnRefreshLoadMoreListener {
    var adapter: TestDataAdapter? = null
    var sIsScrolling: Boolean = false;

    companion object {
        fun newInstance(tag:String)= TestFragment().apply {
            var arguments = Bundle(1).apply {
                putString("tag", tag)
            }
        }

    }

    override fun setLayout(savedInstanceState: Bundle?): Int {
        return R.layout.fragment_test
    }

    override fun doWork() {
        initData()
        vb.smrl.setOnRefreshLoadMoreListener(this)
        vb.smrl.setEnableAutoLoadMore(false)
        vm.mRvData.observe(this, rvDataObserver)
        vb.testRv.layoutManager = LinearLayoutManager(mCtx)
        vb.testRv.adapter = adapter
        val obj = object : OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_DRAGGING || newState == RecyclerView.SCROLL_STATE_SETTLING) {
                    sIsScrolling = true;
                    Glide.with(mCtx).pauseRequests();
                } else if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (sIsScrolling == true) {
                        Glide.with(mCtx).resumeRequests();

                    }
                    sIsScrolling = false;
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
            }
        }
        vb.testRv.addOnScrollListener(obj)
    }

    override fun handlerMsg(msg: Message) {

    }

    private var page: Int = 1
    var pageSize: Int = 5
    private var data: MutableList<SplashAdBean.AdBean> = arrayListOf()
    private val rvDataObserver by lazy {
        Observer<SplashAdBean> {
            it?.let {
                LogTools.i(TAG, "当前页码:".plus(page))
                if (it.vertical.size >= pageSize) {
                    data = it.vertical.subList(
                        (page - 1) * pageSize,
                        page * pageSize
                    )
                } else {
                    data = it.vertical
                }
                if (page == 1) {
                    adapter!!.refreshData(data)
                } else {
                    adapter!!.addData(data)
                }
                if (data.size >= pageSize) {
                    page++
                }
                LogTools.i(TAG, "当前请求ListSize:".plus(data.size))
            }
            vb.smrl.finishRefresh()
            vb.smrl.finishLoadMore()
        }
    }

    override fun onItemClick(v: View, data: SplashAdBean.AdBean, pos: Int) {
        LogTools.i(TAG, data.toString())

    }

    private fun initData() {
        adapter = TestDataAdapter(vm, this);
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        page = 1
        getTestData()
    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {
        getTestData()
    }

    fun getTestData() {
        vm.getTestData(page)
    }
}
