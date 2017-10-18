package com.custview.balvier.customview.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.custview.balvier.customview.pojos.WeatherPOJO
import com.custview.balvier.customview.presenter.WeatherPresenter
import okhttp3.Cache

/**
 * Created by nithin on 18/10/17.
 */
class WeatherRepositoryNew : WeatherPresenter.ResponseWeatherData {

    var mutableLiveData: MutableLiveData<WeatherPOJO>

    init {
        mutableLiveData = MutableLiveData<WeatherPOJO>()
    }

    fun getWeatherData(cache: Cache): LiveData<WeatherPOJO> {
        WeatherRepos().getWeatherData(cache, this)
        return mutableLiveData
    }


    override fun successWeatherData(weatherPOJO: WeatherPOJO) {
        mutableLiveData.value = weatherPOJO
    }

    override fun failedWeatherData(message: String) {

    }
}