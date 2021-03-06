package com.example.mycleanarchitectureapp.model

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MovieDb {

    private val okHttpClient = HttpLoggingInterceptor().run{
                level= HttpLoggingInterceptor.Level.BODY
                OkHttpClient.Builder().addInterceptor(this).build()
        }

    val service: TheMovieDbService = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(TheMovieDbService::class.java)
}