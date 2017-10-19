package com.custview.balvier.customview.repository

import com.custview.balvier.customview.ApplicationClass
import com.custview.balvier.customview.pojos.WeatherList
import com.custview.balvier.customview.presenter.WeatherPresenter
import com.custview.balvier.customview.restservices.RetrofitClient
import com.custview.balvier.customview.restservices.WeatherAPI
import okhttp3.Cache
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by nithin on 19/10/17.
 */
class ForecastRepos : Callback<WeatherList> {

    var responseWeatherData: WeatherPresenter.ResponseForecastData? = null

    override fun onResponse(call: Call<WeatherList>?, response: Response<WeatherList>?) {
        if (response!!.isSuccessful && response.code() == 200) {
            responseWeatherData?.successForecastData(response.body()!!)
        } else {
            responseWeatherData?.failedForecastData(response.errorBody().toString())
        }

    }

    override fun onFailure(call: Call<WeatherList>?, t: Throwable?) {
        t?.printStackTrace()
        responseWeatherData?.failedForecastData(t?.message.toString())
    }

    fun getForecastData(cache: Cache, responseWeatherData: WeatherPresenter.ResponseForecastData) {
        this.responseWeatherData = responseWeatherData

        RetrofitClient.getRetrofitClient(cache).create(WeatherAPI::class.java)
                .weatherForecast("Mumbai,in", "json", ApplicationClass.getAppId()).enqueue(this)
    }

}