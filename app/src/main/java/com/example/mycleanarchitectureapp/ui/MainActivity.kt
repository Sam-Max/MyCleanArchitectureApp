package com.example.mycleanarchitectureapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mycleanarchitectureapp.model.MovieDb
import com.example.mycleanarchitectureapp.R
import com.example.mycleanarchitectureapp.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        GlobalScope.launch(Dispatchers.Main) {

            val movies= MovieDb.service.listPopularMovies(getString(R.string.api_key))
            val adapter= MovieAdapter(movies.results)
            binding.recycler.adapter= adapter
        }
    }
}