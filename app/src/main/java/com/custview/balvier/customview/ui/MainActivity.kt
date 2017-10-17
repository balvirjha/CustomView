package com.custview.balvier.customview.ui

import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.custview.balvier.customview.R

class MainActivity : AppCompatActivity(), RepoFragment.OnFragmentInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().add(R.id.framelayout, RepoFragment(), RepoFragment::class.simpleName).commit()
    }


    override fun onFragmentInteraction(uri: Uri) {

    }
}
