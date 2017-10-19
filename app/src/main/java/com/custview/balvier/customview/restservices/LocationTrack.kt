package com.custview.balvier.customview.restservices

import android.Manifest
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.os.IBinder
import android.provider.Settings
import android.support.v4.app.ActivityCompat.checkSelfPermission
import android.support.v7.app.AlertDialog
import android.util.Log
import android.widget.Toast

/**
 * Created by nithin on 18/10/17.
 */
class LocationTrack(var mContext: Context) : Service(), LocationListener {

    init {
        getLocation()
    }


    var checkGPS: Boolean = false

    var checkNetwork: Boolean = false

    var canGetLocation: Boolean = false

    var loc: Location? = null
    var latitude: Double? = null
    var longitude: Double? = null

    companion object {
        private var MIN_DISTANCE_CHANGE_FOR_UPDATES: Float = 10.0F
        private var MIN_TIME_BW_UPDATES: Long = 1000 * 60 * 1 as Long
    }

    lateinit var locationManager: LocationManager


    private fun getLocation(): Location? {

        try {
            locationManager = mContext.getSystemService(LOCATION_SERVICE) as LocationManager

            checkGPS = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)

            checkNetwork = locationManager
                    .isProviderEnabled(LocationManager.NETWORK_PROVIDER)

            if (!checkGPS && !checkNetwork) {
                Toast.makeText(mContext, "No Service Provider is available", Toast.LENGTH_SHORT).show()
            } else {
                this.canGetLocation = true

                if (checkGPS) {

                    if (checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                    }
                    locationManager.requestLocationUpdates(
                            LocationManager.GPS_PROVIDER,
                            MIN_TIME_BW_UPDATES,
                            MIN_DISTANCE_CHANGE_FOR_UPDATES, this)
                    if (locationManager != null) {
                        loc = locationManager
                                .getLastKnownLocation(LocationManager.GPS_PROVIDER)
                        if (loc != null) {
                            latitude = loc!!.latitude
                            longitude = loc!!.longitude
                        } else {
                            loc = locationManager
                                    .getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
                            latitude = loc!!.latitude
                            longitude = loc!!.longitude
                        }
                    }


                }

            }


        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("bvc", e.printStackTrace().toString())
        }

        return loc
    }

    fun getLongitudeSelf(): Double? {
        if (loc != null) {
            longitude = loc!!.longitude
        }
        return longitude
    }

    fun getLatitudeSelf(): Double? {
        if (loc != null) {
            latitude = loc!!.latitude
        }
        return latitude
    }

    fun canGetLocation(): Boolean? {
        return this.canGetLocation
    }

    fun showSettingsAlert() {
        var alertDialog = AlertDialog.Builder(mContext).create()


        alertDialog.setTitle("GPS is not Enabled!")

        alertDialog.setMessage("Do you want to turn on GPS?")

        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Yes", { dialogInterface, i ->
            var intent: Intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            mContext.startActivity(intent)
        })

        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "No", { dialogInterface, i ->
            alertDialog.cancel()
        })

        alertDialog.show()
    }


    fun stopListener() {
        if (locationManager != null) {

            if (checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return
            }
            locationManager.removeUpdates(this)
        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onLocationChanged(location: Location) {
        Log.e("bvc", "OnLocation changed" + location.latitude)
    }

    override fun onStatusChanged(s: String, i: Int, bundle: Bundle) {

    }

    override fun onProviderEnabled(s: String) {

    }

    override fun onProviderDisabled(s: String) {

    }
}