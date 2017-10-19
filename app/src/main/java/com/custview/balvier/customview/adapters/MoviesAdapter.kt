package com.custview.balvier.customview.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.custview.balvier.customview.R
import com.custview.balvier.customview.pojos.CityList
import kotlinx.android.synthetic.main.movie_list_row.view.*


/**
 * Created by Balvier on 10/15/2017.
 */
class MoviesAdapter(var moviesList: List<CityList>, val listener: OnItemClickListener) : RecyclerView.Adapter<MoviesAdapter.MyViewHolder>() {

    companion object {
        interface OnItemClickListener {
            fun OnItemClick(movie: CityList)
        }
    }

    override fun onBindViewHolder(holder: MyViewHolder?, position: Int) {
        holder?.bindItems(moviesList.get(position))
    }

    override fun getItemCount(): Int = moviesList.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyViewHolder = MyViewHolder(LayoutInflater.from(parent?.context)
            .inflate(R.layout.movie_list_row, parent, false))

    inner class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun bindItems(movie: CityList) {
            view.cityName?.text = movie.name

            view.setOnClickListener(object : View.OnClickListener {
                override fun onClick(p0: View?) {
                    listener.OnItemClick(movie)
                    Toast.makeText(view.context, "Row clicked", Toast.LENGTH_LONG).show()
                }
            })
        }

    }
}