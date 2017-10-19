package com.custview.balvier.customview.repository

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
class WeatherRepos : Callback<WeatherPOJO> {

    var responseWeatherData: WeatherPresenter.ResponseWeatherData? = null

    override fun onResponse(call: Call<WeatherPOJO>?, response: Response<WeatherPOJO>?) {
        if (response!!.isSuccessful && response.code() == 200) {
            responseWeatherData?.successWeatherData(response.body()!!)
        } else {
            responseWeatherData?.failedWeatherData(response.errorBody().toString())
        }

    }

    override fun onFailure(call: Call<WeatherPOJO>?, t: Throwable?) {
        t?.printStackTrace()
        responseWeatherData?.failedWeatherData(t?.message.toString())
    }

    fun getWeatherData(cache: Cache, responseWeatherData: WeatherPresenter.ResponseWeatherData) {
        this.responseWeatherData = responseWeatherData

        RetrofitClient.getRetrofitClient(cache).create(WeatherAPI::class.java)
                .weatherData("Mumbai", ApplicationClass.getAppId()).enqueue(this)
    }

}