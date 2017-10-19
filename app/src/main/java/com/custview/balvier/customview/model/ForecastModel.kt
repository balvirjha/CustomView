package com.custview.balvier.customview.model

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.custview.balvier.customview.ApplicationClass
import com.custview.balvier.customview.pojos.WeatherList
import com.custview.balvier.customview.repository.ForecastRepositoryNew
import okhttp3.Cache

/**
 * Created by nithin on 19/10/17.
 */
class ForecastModel : ViewModel() {

    private var weatherPojo: MutableLiveData<WeatherList>

    init {
        weatherPojo = ForecastRepositoryNew().getForecastData(Cache(ApplicationClass.getApplicationInstance().cacheDir, 10 * 1024 * 1024L)) as MutableLiveData<WeatherList>
    }

    fun getForecastData(): LiveData<WeatherList> {
        return weatherPojo
    }
}