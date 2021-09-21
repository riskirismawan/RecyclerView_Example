package com.riski.recyclerviewexample.data.remote.api


import com.riski.recyclerviewexample.data.remote.response.MovieResponse
import com.riski.recyclerviewexample.data.remote.response.MoviesItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("discover/movie")
    fun getMovie(
        @Query("api_key") api_key: String = "003b76808a270c1366be2601621229bf"
    ): Call<MovieResponse>

    @GET("movie/{movie_id}")
    fun getMovieById(
        @Path("movie_id") movieId: Int,
        @Query("api_key") api_key: String = "003b76808a270c1366be2601621229bf"
    ): Call<MoviesItem>
}