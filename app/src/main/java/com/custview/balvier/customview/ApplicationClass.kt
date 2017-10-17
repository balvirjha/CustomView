package com.custview.balvier.customview

import android.app.Application

/**
 * Created by nithin on 17/10/17.
 */
class ApplicationClass : Application() {

    companion object {
        private lateinit var instance: ApplicationClass
        val BASE_URL = "http://api.openweathermap.org"
        private val APPID = "a363a2b2ee3e14ddb617d983afe213f0"
        fun getApplicationInstance() = instance!!
        fun getAppId() = APPID
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}