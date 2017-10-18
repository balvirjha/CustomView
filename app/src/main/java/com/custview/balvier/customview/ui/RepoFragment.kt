package com.custview.balvier.customview.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.custview.balvier.customview.R
import com.custview.balvier.customview.model.WeatherModel
import com.custview.balvier.customview.pojos.WeatherPOJO
import kotlinx.android.synthetic.main.fragment_repo.*
import java.text.SimpleDateFormat
import java.util.*


class RepoFragment : Fragment(), Observer<WeatherPOJO> {

    private lateinit var viewModel: WeatherModel

    override fun onChanged(weatherPOJO: WeatherPOJO?) {
        if (activity != null && !activity.isFinishing) {
            Log.e("bvc", "response successfull")
            cityName.text = weatherPOJO?.name
            temp.text = weatherPOJO?.main?.temp.toString()
            humidity.text = weatherPOJO?.main?.humidity.toString()
            wind.text = weatherPOJO?.wind?.speed.toString() + " " + weatherPOJO?.wind?.deg.toString()
            var cal: Calendar = Calendar.getInstance()
            cal.timeInMillis = weatherPOJO?.sys?.sunrise.toString().toLong()
            sunrise.text = SimpleDateFormat("hh:mm").format(cal.time)
            var cal1: Calendar = Calendar.getInstance()
            cal1.timeInMillis = weatherPOJO?.sys?.sunset.toString().toLong()
            sunset.text = SimpleDateFormat("hh:mm").format(cal1.time)
        }
    }

    private var mListener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {

        }
    }

    var mRoot: View? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        mRoot = inflater!!.inflate(R.layout.fragment_repo, container, false)
        return mRoot
    }

    fun Context.isNetworkAvailable(): Boolean {
        var connectivitymanager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivitymanager.activeNetworkInfo.isConnected)
            return true

        return false

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var imageWeather = mRoot?.findViewById<ImageView>(R.id.imageWeather)

//        GlideApp.with(this)
//                .load("https://www.pinterest.com/pin/516928863460594822/")
//                .centerInside()
//                .into(imageWeather)
        viewModel = ViewModelProviders.of(this).get(WeatherModel::class.java)
        viewModel.getWeatherData().observe(this, this)

    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            mListener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    interface OnFragmentInteractionListener {

        fun onFragmentInteraction(uri: Uri)
    }

}