package com.example.mycleanarchitectureapp.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mycleanarchitectureapp.R
import com.example.mycleanarchitectureapp.databinding.ViewMovieBinding
import com.example.mycleanarchitectureapp.model.Movie

class MovieAdapter(
    private val context: Context,
    var movies: List<Movie>
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view: View = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.view_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val binding= ViewMovieBinding.bind(holder.view)
        binding.movieTitle.text= movies[position].title
        Glide.with(context)
            .load("https://image.tmdb.org/t/p/w185/${movies[position].posterPath}")
            .into(binding.movieCover)
    }

    override fun getItemCount(): Int = movies.size

    inner class MovieViewHolder(val view: View) : RecyclerView.ViewHolder(view)
}