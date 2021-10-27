package com.example.mycleanarchitectureapp

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.logging.Logger

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