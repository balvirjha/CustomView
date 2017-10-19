package com.custview.balvier.customview.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.custview.balvier.customview.R
import com.custview.balvier.customview.adapters.MoviesAdapter
import com.custview.balvier.customview.pojos.CityList
import com.custview.balvier.customview.pojos.CityListserizable
import com.google.gson.Gson
import kotlinx.android.synthetic.main.city_list_fragment.*
import java.io.IOException

/**
 * Created by nithin on 18/10/17.
 */
class CityListFragment : Fragment() {


    lateinit var mRoot: View

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mRoot = inflater!!.inflate(R.layout.city_list_fragment, container, false)
        return mRoot
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        cityRecycler.layoutManager = LinearLayoutManager(activity)
        cityRecycler.itemAnimator = DefaultItemAnimator()
        val adapter = MoviesAdapter(readAssetFile()!!.cityList, object : MoviesAdapter.Companion.OnItemClickListener {
            override fun OnItemClick(movie: CityList) {
                Toast.makeText(activity, "Row clicked " + movie.name, Toast.LENGTH_LONG).show()
            }
        })
        cityRecycler.adapter = adapter
        cityRecycler.adapter.notifyDataSetChanged()
    }

    fun readAssetFile(): CityListserizable? {
        var json: String? = null
        var eventResponseList: CityListserizable? = null
        try {
            val inputStream = activity.assets.open("citylist.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer)
            json.replace("\n", "")
            eventResponseList = Gson().fromJson(json, CityListserizable::class.java)

        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            return eventResponseList
        }

    }
}