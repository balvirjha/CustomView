package com.custview.balvier.customview.presenter

import com.custview.balvier.customview.pojos.WeatherList
import com.custview.balvier.customview.pojos.WeatherPOJO

/**
 * Created by nithin on 17/10/17.
 */
interface WeatherPresenter {

    interface ResponseWeatherData {
        fun successWeatherData(weatherPOJO: WeatherPOJO)
        fun failedWeatherData(message: String)
    }

    interface ResponseForecastData {
        fun successForecastData(weatherPOJO: WeatherList)
        fun failedForecastData(message: String)
    }
}