package com.custview.balvier.customview

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.movie_list_row.view.*


/**
 * Created by Balvier on 10/15/2017.
 */
class MoviesAdapter(var moviesList: List<Movie>, val listener: OnItemClickListener) : RecyclerView.Adapter<MoviesAdapter.MyViewHolder>() {

    companion object {
        public interface OnItemClickListener {
            fun OnItemClick(movie: Movie)
        }
    }

    override fun onBindViewHolder(holder: MyViewHolder?, position: Int) {
        holder?.bindItems(moviesList.get(position))
    }

    override fun getItemCount(): Int = moviesList.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyViewHolder = MyViewHolder(LayoutInflater.from(parent?.getContext())
            .inflate(R.layout.movie_list_row, parent, false))

    inner class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun bindItems(movie: Movie) {
            view.title?.text = movie.title
            view.genre?.text = movie.genre
            view.year?.text = movie.year

            view.setOnClickListener(object : View.OnClickListener {
                override fun onClick(p0: View?) {
                    listener.OnItemClick(movie)
                    Toast.makeText(view.context, "Row clicked", Toast.LENGTH_LONG).show()
                }
            })
        }

    }
}