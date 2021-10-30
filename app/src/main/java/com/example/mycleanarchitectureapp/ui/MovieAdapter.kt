package com.example.mycleanarchitectureapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mycleanarchitectureapp.R
import com.example.mycleanarchitectureapp.databinding.ViewMovieBinding
import com.example.mycleanarchitectureapp.model.Movie

class MovieAdapter (var movies: List<Movie>) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view: View = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.view_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val binding= ViewMovieBinding.bind(holder.view)
        binding.movieTitle.text= movies.get(position).title
    }

    override fun getItemCount(): Int = movies.size

    inner class MovieViewHolder(val view: View) : RecyclerView.ViewHolder(view)
}