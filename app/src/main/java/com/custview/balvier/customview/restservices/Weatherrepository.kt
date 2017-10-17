package com.custview.balvier.customview.restservices

import com.custview.balvier.customview.ApplicationClass
import com.custview.balvier.customview.pojos.WeatherPOJO
import com.custview.balvier.customview.presenter.WeatherPresenter
import okhttp3.Cache
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by nithin on 17/10/17.
 */
class Weatherrepository : Callback<WeatherPOJO> {

    var weatherPresenterCallback: WeatherPresenter.ResponseWeatherData? = null

    override fun onResponse(call: Call<WeatherPOJO>?, response: Response<WeatherPOJO>?) {
        if (response!!.isSuccessful && response?.code() == 200) {
            weatherPresenterCallback?.successWeatherData(response.body()!!)
        } else {
            weatherPresenterCallback?.failedWeatherData(response.errorBody().toString())
        }

    }

    override fun onFailure(call: Call<WeatherPOJO>?, t: Throwable?) {
        t?.printStackTrace()
        weatherPresenterCallback?.failedWeatherData(t?.message.toString())
    }

    fun getWeatherData(weatherPresenterCallback: WeatherPresenter.ResponseWeatherData, cache: Cache) {
        this.weatherPresenterCallback = weatherPresenterCallback
        RetrofitClient.getRetrofitClient(cache).create(WeatherAPI::class.java)
                .weatherData("Mumbai", ApplicationClass.getAppId()).enqueue(this)
    }

}