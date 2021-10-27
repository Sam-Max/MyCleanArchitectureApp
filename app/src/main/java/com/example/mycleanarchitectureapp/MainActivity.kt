package com.example.mycleanarchitectureapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch(Dispatchers.Main) {

            val movies: MovieDbResult = MovieDb.service.listPopularMovies(getString(R.string.api_key))

            Toast.makeText(applicationContext, movies.totalResult.toString(), Toast.LENGTH_LONG)
                .show()
        }
    }
}