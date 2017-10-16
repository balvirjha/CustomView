package com.custview.balvier.customview

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recycler_view.layoutManager = LinearLayoutManager(this)
        val users = ArrayList<Movie>()

        users.add(Movie("Belal Khan", "Ranchi Jharkhand", "2004"))
        users.add(Movie("Ramiz Khan", "Ranchi Jharkhand", "2007"))
        users.add(Movie("Faiz Khan", "Ranchi Jharkhand", "2008"))
        users.add(Movie("Yashar Khan", "Ranchi Jharkhand", "2010"))
        users.add(Movie("Yashar Khan", "Ranchi Jharkhand", "2010"))
        users.add(Movie("Yashar Khan", "Ranchi Jharkhand", "2010"))
        users.add(Movie("Yashar Khan", "Ranchi Jharkhand", "2010"))
        users.add(Movie("Yashar Khan", "Ranchi Jharkhand", "2010"))
        users.add(Movie("Yashar Khan", "Ranchi Jharkhand", "2010"))
        users.add(Movie("Yashar Khan", "Ranchi Jharkhand", "2010"))
        users.add(Movie("Yashar Khan", "Ranchi Jharkhand", "2010"))
        users.add(Movie("Yashar Khan", "Ranchi Jharkhand", "2010"))
        users.add(Movie("Yashar Khan", "Ranchi Jharkhand", "2010"))
        users.add(Movie("Yashar Khan", "Ranchi Jharkhand", "2010"))
        users.add(Movie("Yashar Khan", "Ranchi Jharkhand", "2010"))
        users.add(Movie("Yashar Khan", "Ranchi Jharkhand", "2010"))
        users.add(Movie("Yashar Khan", "Ranchi Jharkhand", "2010"))
        users.add(Movie("Yashar Khan", "Ranchi Jharkhand", "2010"))
        users.add(Movie("Yashar Khan", "Ranchi Jharkhand", "2010"))
        users.add(Movie("Yashar Khan", "Ranchi Jharkhand", "2010"))
        users.add(Movie("Yashar Khan", "Ranchi Jharkhand", "2010"))
        users.add(Movie("Yashar Khan", "Ranchi Jharkhand", "2010"))
        users.add(Movie("Yashar Khan", "Ranchi Jharkhand", "2010"))
        users.add(Movie("Yashar Khan", "Ranchi Jharkhand", "2010"))
        users.add(Movie("Yashar Khan", "Ranchi Jharkhand", "2010"))
        users.add(Movie("Yashar Khan", "Ranchi Jharkhand", "2010"))
        users.add(Movie("Yashar Khan", "Ranchi Jharkhand", "2010"))
        users.add(Movie("Yashar Khan", "Ranchi Jharkhand", "2010"))
        users.add(Movie("Yashar Khan", "Ranchi Jharkhand", "2010"))
        users.add(Movie("Yashar Khan", "Ranchi Jharkhand", "2010"))
        users.add(Movie("Yashar Khan", "Ranchi Jharkhand", "2010"))
        users.add(Movie("Yashar Khan", "Ranchi Jharkhand", "2010"))
        users.add(Movie("Yashar Khan", "Ranchi Jharkhand", "2010"))
        users.add(Movie("Yashar Khan", "Ranchi Jharkhand", "2010"))
        users.add(Movie("Yashar Khan", "Ranchi Jharkhand", "2010"))
        users.add(Movie("Yashar Khan", "Ranchi Jharkhand", "2010"))

        val adapter = MoviesAdapter(users, object : MoviesAdapter.Companion.OnItemClickListener {
            override fun OnItemClick(movie: Movie) {
                Toast.makeText(this@MainActivity, "Row clicked " + movie.title, Toast.LENGTH_LONG).show()
            }
        });

        recycler_view.adapter = adapter
        recycler_view.adapter.notifyDataSetChanged()
    }
}
