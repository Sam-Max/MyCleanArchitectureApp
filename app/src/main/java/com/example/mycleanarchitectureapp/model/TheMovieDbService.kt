package com.example.mycleanarchitectureapp.model

import retrofit2.http.GET
import retrofit2.http.Query

interface TheMovieDbService {

    @GET("discover/movie?sort_by=popularity.desc")
    suspend fun listPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("region") region: String
    ): MovieDbResult
}