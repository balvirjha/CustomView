package com.custview.balvier.customview.model

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.custview.balvier.customview.ApplicationClass
import com.custview.balvier.customview.pojos.WeatherPOJO
import com.custview.balvier.customview.repository.Weatherrepository
import okhttp3.Cache

/**
 * Created by nithin on 17/10/17.
 */
class WeatherModel : ViewModel() {

    private var weatherPojo: MutableLiveData<WeatherPOJO>

    init {
        weatherPojo = Weatherrepository().getWeatherData(Cache(ApplicationClass.getApplicationInstance().getCacheDir(), 10 * 1024 * 1024L)) as MutableLiveData<WeatherPOJO>
    }

    fun getWeatherData(): LiveData<WeatherPOJO> {
        return weatherPojo
    }
}