package com.custview.balvier.customview.pojos

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


/**
 * Created by nithin on 18/10/17.
 */
class CityList {

    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("country")
    @Expose
    var country: String? = null
    @SerializedName("coord")
    @Expose
    var coord: Coord? = null

}