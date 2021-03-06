package com.example.mycleanarchitectureapp.ui.common

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import com.bumptech.glide.Glide
import com.example.mycleanarchitectureapp.databinding.ActivityDetailBinding
import com.example.mycleanarchitectureapp.model.Movie

class DetailActivity : AppCompatActivity() {

    companion object {
        const val MOVIE= "DetailActivity:movie"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding= ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.getParcelableExtra<Movie>(MOVIE)?.run{

            binding.movieDetailToolbar.title= title

            Glide.with(this@DetailActivity)
                .load("https://image.tmdb.org/t/p/w780$posterPath")
                .into(binding.movieDetailImage)

            binding.movieDetailSummary.text = overview

            binding.movieDetailInfo.text = buildSpannedString {

                bold { append("Original language:") }
                appendLine(originalLanguage)
            }
        }
    }
}