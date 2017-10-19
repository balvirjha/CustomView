package com.custview.balvier.customview.ui

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.annotation.TargetApi
import android.content.Context
import android.content.DialogInterface
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.custview.balvier.customview.R
import com.custview.balvier.customview.restservices.LocationTrack
import java.util.*


class MainActivity : AppCompatActivity(), RepoFragment.OnFragmentInteractionListener {
    private var permissionsToRequest: ArrayList<String>? = null
    private val permissionsRejected = ArrayList<String>()
    private val permissions = ArrayList<String>()
    private val ALL_PERMISSIONS_RESULT = 101
    lateinit var locationTrack: LocationTrack
    var longitude = 0.0
    var latitude = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var latgSharedPref = getSharedPreferences("lat", Context.MODE_PRIVATE) as SharedPreferences
        var longSharedPref = getSharedPreferences("long", Context.MODE_PRIVATE)
        if (latgSharedPref.getFloat("lat", 0F) == 0F) {
            permissions.add(ACCESS_FINE_LOCATION)
            permissions.add(ACCESS_COARSE_LOCATION)
            permissionsToRequest = findUnAskedPermissions(permissions)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (permissionsToRequest?.size!! > 0) {
                    requestPermissions(permissionsToRequest?.toArray(arrayOfNulls<String>(permissionsToRequest?.size!!)), ALL_PERMISSIONS_RESULT)
                }
            }

            locationTrack = LocationTrack(this)

            if (locationTrack.canGetLocation()!!) {

                longitude = locationTrack.longitude!!
                latitude = locationTrack.latitude!!


                latgSharedPref.edit().putFloat("lat", latitude.toFloat()).commit()

                longSharedPref.edit().putFloat("long", longitude.toFloat()).commit()

                Toast.makeText(this, "Longitude:" + longitude + "\nLatitude:" + latitude, Toast.LENGTH_SHORT).show()
            } else {

                locationTrack.showSettingsAlert()
            }
        } else {
            Log.e("bvc", "lat long already present")
            longitude = latgSharedPref.getFloat("lat", 0F).toDouble()
            latitude = latgSharedPref.getFloat("long", 0F).toDouble()
        }
        supportFragmentManager.beginTransaction().add(R.id.framelayout, RepoFragment(), RepoFragment::class.simpleName).commit()
    }


    override fun onFragmentInteraction(uri: Uri) {

    }

    private fun findUnAskedPermissions(wanted: ArrayList<String>): ArrayList<String> {
        val result = ArrayList<String>()

        for (perm in wanted) {
            if (!hasPermission(perm)) {
                result.add(perm)
            }
        }

        return result
    }

    private fun hasPermission(permission: String): Boolean {
        if (canMakeSmores()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                return checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
            }
        }
        return true
    }

    private fun canMakeSmores(): Boolean {
        return Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1
    }

    @TargetApi(Build.VERSION_CODES.M)
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {

        when (requestCode) {

            ALL_PERMISSIONS_RESULT -> {
                for (perms in permissionsToRequest!!) {
                    if (!hasPermission(perms)) {
                        permissionsRejected.add(perms)
                    }
                }

                if (permissionsRejected.size > 0) {


                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (shouldShowRequestPermissionRationale(permissionsRejected[0])) {
                            showMessageOKCancel("These permissions are mandatory for the application. Please allow access.",
                                    DialogInterface.OnClickListener { dialog, which ->
                                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                            requestPermissions(permissionsRejected.toArray(arrayOfNulls(permissionsRejected.size)), ALL_PERMISSIONS_RESULT)
                                        }
                                    })
                            return
                        }
                    }

                }
            }
        }

    }

    private fun showMessageOKCancel(message: String, okListener: DialogInterface.OnClickListener) {
        AlertDialog.Builder(this@MainActivity)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show()
    }

    override fun onDestroy() {
        super.onDestroy()
        locationTrack.stopListener()
    }

}
