package com.custview.balvier.customview.pojos

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by nithin on 19/10/17.
 */

class City {

    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("coord")
    @Expose
    var coord: Coord? = null
    @SerializedName("country")
    @Expose
    var country: String? = null

}
