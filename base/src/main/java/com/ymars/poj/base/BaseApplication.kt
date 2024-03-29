package com.ymars.poj.base

import android.app.Application
import android.content.Context
import com.alibaba.android.arouter.launcher.ARouter
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.ClassicsHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.ymars.poj.comutils.LogTools


open class BaseApplication : Application() {

    companion object {
        private var instance: BaseApplication? = null
            get() {
                if (field == null) {
                    field = BaseApplication()
                    initSmartRefreshLayout()
                }
                return field
            }

        private fun initSmartRefreshLayout() {
            SmartRefreshLayout.setDefaultRefreshHeaderCreator { context: Context, layout: RefreshLayout ->
                layout.setPrimaryColorsId(R.color.app_theme_title_color, android.R.color.white);
                ClassicsHeader(context)
            }
            SmartRefreshLayout.setDefaultRefreshFooterCreator { context: Context, layout: RefreshLayout ->
                layout.setPrimaryColorsId(R.color.app_theme_title_color, android.R.color.white);
                ClassicsFooter(context)
            }
        }

        @Synchronized
        fun get(): BaseApplication {
            return instance!!
        }

    }

    val TAG: String by lazy {
        this.javaClass.simpleName
    }

    override fun onCreate() {
        super.onCreate()
        initModuleApp(this)
        initArouter()
    }

    private fun initArouter() {
        if (BuildConfig.DEBUG) {
            //下面两句话必须放到init前面,否则配置无效
            ARouter.openLog();  //打印ARouter日志
            ARouter.openDebug();  //开启ARouter的调试模式(如果在InstantRun模式下运行,必须开启调试模式,线上版本需要关闭，否则有安全风险),
        }
        //官方建议在Application里面进行初始化(使用该注解路径至少两级)
        ARouter.init(this);
        LogTools.i(TAG, "initArouter")
    }

    open fun initModuleApp(application: BaseApplication?) {

    }

}