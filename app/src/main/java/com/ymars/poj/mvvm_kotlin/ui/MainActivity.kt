package com.ymars.poj.mvvm_kotlin.ui

import android.os.Bundle
import android.os.Message
import android.util.SparseArray
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.alibaba.android.arouter.facade.annotation.Route
import com.mars.splash.SplashActivity
import com.ymars.news.ui.fragment.NewsMainFragment
import com.ymars.poj.base.adapter.CustFragmentPagerAdapter
import com.ymars.poj.base.ui.LifecycerActivity
import com.ymars.poj.component.ArouterConstant
import com.ymars.poj.comutils.LogTools
import com.ymars.poj.comutils.bean.SwitchIconTask
import com.ymars.poj.comutils.manager.LauncherIconManager
import com.ymars.poj.mvvm_kotlin.R
import com.ymars.poj.mvvm_kotlin.adapter.MainTabAdapter
import com.ymars.poj.mvvm_kotlin.bean.TabBean
import com.ymars.poj.mvvm_kotlin.databinding.ActivityMainBinding
import com.ymars.poj.mvvm_kotlin.model.MainViewModel
import java.text.SimpleDateFormat

@Route(path = ArouterConstant.APP_MAIN)
class MainActivity : LifecycerActivity<MainViewModel, ActivityMainBinding>() {
    var adapter: MainTabAdapter? = null
    var txt: String = "首页"
    var fragmentAdapter: CustFragmentPagerAdapter? = null
    var newsMainFragment: NewsMainFragment? = null
    var testFragment1: TestFragment? = null
    var testFragment2: TestFragment? = null
    var listFragment: SparseArray<Fragment>? = null

    override fun setLayout(savedInstanceState: Bundle?): Int {
        return R.layout.activity_main
    }

    override fun initParams(savedInstanceState: Bundle?) {
        super.initParams(savedInstanceState)
        vm.tabBeans.observe(this, observer)
        vm.tabSelectPos.observe(this, posObserver)
        val layoutManager = GridLayoutManager(this, vm.tabBeans.value!!.size)
        vb.tabRv.layoutManager = layoutManager
        newsMainFragment = NewsMainFragment.newInstance("新闻")
        testFragment1 = TestFragment.newInstance("视频")
        testFragment2 = TestFragment.newInstance("我的")

        listFragment = SparseArray()
        listFragment!!.put(0, newsMainFragment!!)
        listFragment!!.put(1, testFragment1!!)
        listFragment!!.put(2, testFragment2!!)

        fragmentAdapter =
            CustFragmentPagerAdapter(supportFragmentManager, lifecycle, listFragment!!)
        adapter = MainTabAdapter(vm)


    }

    override fun handlerMsg(msg: Message) {
    }

    override fun doWork() {
        vm.getTabBean()
        vb.tabRv.adapter = adapter
        vb.vp2.adapter = fragmentAdapter
        vb.vp2.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                LogTools.i(TAG, "Viewpager2 滑动:" + position)
                vm.tabBeans.value?.get(position)?.state = 1
            }
        })


        val format = SimpleDateFormat("yyyy-MM-dd")

        LauncherIconManager.addNewTask(
            SwitchIconTask(
                SplashActivity::
                class.java.name,
                "$packageName.SplashAlias1Activity",
                format.parse("2021-06-09").time,
                format.parse("2021-06-10").time
            ),
            SwitchIconTask(
                SplashActivity::
                class.java.name,
                "$packageName.SplashAlias2Activity",
                format.parse("2021-06-11").time,
                format.parse("2021-06-13").time
            )
        )

    }

    private val observer by lazy {
        Observer<ArrayList<TabBean>> {
            it?.let {
                adapter!!.refreshData(it)
            }
        }
    }
    private val posObserver by lazy {
        Observer<Int> {
            it?.let {
                LogTools.i(TAG, vm.tabBeans.value?.get(it).toString())
                txt = vm.tabBeans.value?.get(it)?.txt!!
                when (it) {
                    0 -> vb.vp2.currentItem = 0
                    1 -> vb.vp2.currentItem = 1
                    2 -> vb.vp2.currentItem = 2
                }
            }
        }
    }

}
