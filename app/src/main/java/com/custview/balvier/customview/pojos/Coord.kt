package com.custview.balvier.customview.pojos

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by nithin on 17/10/17.
 */

class Coord {

    @SerializedName("lon")
    @Expose
    var lon: Float? = null
    @SerializedName("lat")
    @Expose
    var lat: Float? = null

}
