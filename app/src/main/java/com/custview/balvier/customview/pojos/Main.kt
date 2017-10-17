package com.custview.balvier.customview.pojos

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by nithin on 17/10/17.
 */

class Main {

    @SerializedName("temp")
    @Expose
    var temp: Float? = null
    @SerializedName("pressure")
    @Expose
    var pressure: Int? = null
    @SerializedName("humidity")
    @Expose
    var humidity: Int? = null
    @SerializedName("temp_min")
    @Expose
    var tempMin: Float? = null
    @SerializedName("temp_max")
    @Expose
    var tempMax: Float? = null

}