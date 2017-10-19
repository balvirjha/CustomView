package com.custview.balvier.customview.restservices

import com.custview.balvier.customview.pojos.WeatherList
import com.custview.balvier.customview.pojos.WeatherPOJO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by nithin on 17/10/17.
 */
interface WeatherAPI {

    @GET("/data/2.5/weather")
    fun weatherData(@Query("q") city: String, @Query("APPID") appid: String): Call<WeatherPOJO>

    @GET("/data/2.5/forecast")
    fun weatherForecast(@Query("q") city: String, @Query("mode") mode: String, @Query("APPID") appid: String): Call<WeatherList>
}