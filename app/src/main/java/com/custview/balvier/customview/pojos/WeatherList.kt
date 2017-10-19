package com.custview.balvier.customview.pojos

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by nithin on 19/10/17.
 */

class WeatherList {

    @SerializedName("cod")
    @Expose
    var cod: String? = null
    @SerializedName("message")
    @Expose
    var message: Float? = null
    @SerializedName("cnt")
    @Expose
    var cnt: Int? = null
    @SerializedName("list")
    @Expose
    var list: List<List<Any?>>? = null
    @SerializedName("city")
    @Expose
    var city: City? = null

}
