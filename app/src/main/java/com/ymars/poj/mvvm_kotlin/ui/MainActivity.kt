package com.ymars.poj.mvvm_kotlin.ui

import android.os.Bundle
import android.os.Message
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.mars.splash.SplashActivity
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

    var testFragment: TestFragment? = null
    override fun setLayout(savedInstanceState: Bundle?): Int {
        return R.layout.activity_main
    }

    override fun initParams(savedInstanceState: Bundle?) {
        super.initParams(savedInstanceState)
        vm.tabBeans.observe(this, observer)
        vm.tabSelectPos.observe(this, posObserver)
        val layoutManager = GridLayoutManager(this, vm.tabBeans.value!!.size)
        vb.tabRv.layoutManager = layoutManager
        testFragment = TestFragment()
        replaceFragment(testFragment!!)
    }

    override fun handlerMsg(msg: Message) {
    }

    override fun doWork() {
        vm.getTabBean()
        adapter = MainTabAdapter(vm)
        vb.tabRv.adapter = adapter

        val format = SimpleDateFormat("yyyy-MM-dd")

        LauncherIconManager.addNewTask(
            SwitchIconTask(
                SplashActivity::class.java.name,
                "$packageName.SplashAlias1Activity",
                format.parse("2021-06-09").time,
                format.parse("2021-06-10").time
            ),
            SwitchIconTask(
                SplashActivity::class.java.name,
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
                testFragment!!.vm.mTxt.postValue(txt)

            }
        }
    }

    private fun replaceFragment(fragment: TestFragment) {
        val fragmentManager = supportFragmentManager //获取FragmentManager
        val transaction = fragmentManager.beginTransaction() //开启一个事务
        transaction.replace(R.id.testTv, fragment)  //替换容器内的fragment
//        transaction.addToBackStack(null)    //返回栈,实现按下back键返回上一个fragment
        transaction.commit()    //提交事务
    }
}
