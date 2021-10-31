package com.example.mycleanarchitectureapp.ui

import android.content.Context
import android.os.Bundle
import com.example.mycleanarchitectureapp.R
import com.example.mycleanarchitectureapp.databinding.ActivityMainBinding
import com.example.mycleanarchitectureapp.model.MovieDb
import com.example.mycleanarchitectureapp.ui.common.CoroutineScopeActivity
import kotlinx.coroutines.launch

class MainActivity : CoroutineScopeActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        launch {
            val movies= MovieDb.service.listPopularMovies(getString(R.string.api_key))
            val adapter= MovieAdapter(this@MainActivity, movies.results)
            binding.recycler.adapter= adapter
        }
    }
}