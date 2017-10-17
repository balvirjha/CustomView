package com.custview.balvier.customview.pojos

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by nithin on 17/10/17.
 */

class Wind {

    @SerializedName("speed")
    @Expose
    var speed: Float? = null
    @SerializedName("deg")
    @Expose
    var deg: Int? = null

}
