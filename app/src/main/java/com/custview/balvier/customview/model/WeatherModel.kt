package com.custview.balvier.customview.model

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.custview.balvier.customview.ApplicationClass
import com.custview.balvier.customview.pojos.WeatherPOJO
import com.custview.balvier.customview.repository.WeatherRepositoryNew
import okhttp3.Cache

/**
 * Created by nithin on 17/10/17.
 */
class WeatherModel : ViewModel() {

    private var weatherPojo: MutableLiveData<WeatherPOJO>

    init {
        weatherPojo = WeatherRepositoryNew().getWeatherData(Cache(ApplicationClass.getApplicationInstance().cacheDir, 10 * 1024 * 1024L)) as MutableLiveData<WeatherPOJO>
    }

    fun getWeatherData(): LiveData<WeatherPOJO> {
        return weatherPojo
    }
}