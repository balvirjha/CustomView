package com.custview.balvier.customview.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.custview.balvier.customview.pojos.WeatherList
import com.custview.balvier.customview.presenter.WeatherPresenter
import okhttp3.Cache

/**
 * Created by nithin on 19/10/17.
 */
class ForecastRepositoryNew : WeatherPresenter.ResponseForecastData {

    var mutableLiveData: MutableLiveData<WeatherList>

    init {
        mutableLiveData = MutableLiveData<WeatherList>()
    }

    fun getForecastData(cache: Cache): LiveData<WeatherList> {
        ForecastRepos().getForecastData(cache, this)
        return mutableLiveData
    }


    override fun successForecastData(weatherPOJO: WeatherList) {
        mutableLiveData.value = weatherPOJO
    }

    override fun failedForecastData(message: String) {

    }
}