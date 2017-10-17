package com.custview.balvier.customview.ui

import android.content.Context
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.custview.balvier.customview.R
import com.custview.balvier.customview.pojos.WeatherPOJO
import com.custview.balvier.customview.presenter.WeatherPresenter
import com.custview.balvier.customview.restservices.Weatherrepository
import com.felipecsl.gifimageview.library.GifImageView
import kotlinx.android.synthetic.main.fragment_repo.*
import okhttp3.Cache


class RepoFragment : Fragment(), WeatherPresenter.ResponseWeatherData {

    override fun successWeatherData(weatherPOJO: WeatherPOJO) {
        Log.e("bvc", "response successfull")
        cityName.text = weatherPOJO.name
        temp.text = weatherPOJO.main?.temp.toString()
        humidity.text = weatherPOJO.main?.humidity.toString()
        wind.text = weatherPOJO.wind?.speed.toString() + " " + weatherPOJO.wind?.deg.toString()
        sunrise.text = weatherPOJO.sys?.sunrise.toString()
        sunset.text = weatherPOJO.sys?.sunset.toString()

    }

    override fun failedWeatherData(message: String) {
        Log.e("bvc", "response not successfull")

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
        return mRoot;
    }

    fun Context.isNetworkAvailable(): Boolean {
        var connectivitymanager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager;
        if (connectivitymanager.activeNetworkInfo.isConnected)
            return true

        return false

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var imageWeather = mRoot?.findViewById<GifImageView>(R.id.imageWeather)
        imageWeather?.startAnimation()
        if (activity.isNetworkAvailable()) {
            Weatherrepository().getWeatherData(this, Cache(activity.getCacheDir(), 10 * 1024 * 1024L))
            Log.e("bvc", "internet available")
        }

    }

    fun onButtonPressed(uri: Uri) {
        if (mListener != null) {
            mListener!!.onFragmentInteraction(uri)
        }
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

    override fun onStop() {
        super.onStop()
        imageWeather?.stopAnimation()
    }

    interface OnFragmentInteractionListener {

        fun onFragmentInteraction(uri: Uri)
    }

}