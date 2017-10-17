package com.custview.balvier.customview.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.custview.balvier.customview.ApplicationClass
import com.custview.balvier.customview.pojos.WeatherPOJO
import com.custview.balvier.customview.presenter.WeatherPresenter
import com.custview.balvier.customview.restservices.RetrofitClient
import com.custview.balvier.customview.restservices.WeatherAPI
import okhttp3.Cache
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by nithin on 17/10/17.
 */
class Weatherrepository : Callback<WeatherPOJO> {

    var weatherPresenterCallback: WeatherPresenter.ResponseWeatherData? = null
    lateinit var weatherPojo: MutableLiveData<WeatherPOJO>

    override fun onResponse(call: Call<WeatherPOJO>?, response: Response<WeatherPOJO>?) {
        if (response!!.isSuccessful && response?.code() == 200) {
            weatherPojo.value = response.body()
        }

    }

    override fun onFailure(call: Call<WeatherPOJO>?, t: Throwable?) {
        t?.printStackTrace()
    }

    fun getWeatherData(cache: Cache): LiveData<WeatherPOJO> {
        this.weatherPresenterCallback = weatherPresenterCallback
        RetrofitClient.getRetrofitClient(cache).create(WeatherAPI::class.java)
                .weatherData("Mumbai", ApplicationClass.getAppId()).enqueue(this)
        return weatherPojo
    }

}