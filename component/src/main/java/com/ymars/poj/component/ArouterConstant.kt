package com.ymars.poj.component

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

@Retention(RetentionPolicy.SOURCE)
annotation class ArouterConstant {
    companion object {
        //App
        const val APP_MAIN: String = "/app/MainActivity"
        const val APP_TEST: String = "/app/TestActivity"
        //Splash
        const val Splash_MAIN: String = "/splash/SplashActivity"

    }
}